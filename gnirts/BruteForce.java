import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class BruteForce {

    private static final String TARGET_HASH = "7c51a5e6ea3214af970a86df89793b19";

    private static String md5Hash(String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashedBytes = md.digest(text.getBytes());

        StringBuilder sb = new StringBuilder();
        for (byte b : hashedBytes) {
            sb.append(String.format("%02x", b & 0xff));
        }

        return sb.toString();
    }

    private static class BruteForceCallable implements Callable<String> {
        private final String characters;
        private final int start;
        private final int end;

        public BruteForceCallable(String characters, int start, int end) {
            this.characters = characters;
            this.start = start;
            this.end = end;
        }

        @Override
        public String call() throws Exception {
            // for (int i = start; i < end; i++) {
            // for (int j = 0; j < characters.length(); j++) {
            // for (int k = 0; k < characters.length(); k++) {
            // for (int m = 0; m < characters.length(); m++) {
            // for (int n = 0; n < characters.length(); n++) {
            // String guess = "" + characters.charAt(i) + characters.charAt(j) +
            // characters.charAt(k) + characters.charAt(m) +
            // characters.charAt(n);
            // String hashedGuess = md5Hash(guess);

            // if (hashedGuess.equals(TARGET_HASH)) {
            // return guess;
            // }
            // }
            // }
            // }
            // }
            // }
            for (int i = start; i < end; i++) {
                for (int j = 0; j < characters.length(); j++) {
                    for (int k = 0; k < characters.length(); k++) {
                        String guess = "" + characters.charAt(i) + characters.charAt(j) +
                                characters.charAt(k);
                        String hashedGuess = md5Hash(guess);

                        if (hashedGuess.equals(TARGET_HASH)) {
                            return guess;
                        }
                    }
                }   
            }

            // for (int i = start; i < end; i++) {
            // for (int j = 0; j < characters.length(); j++) {
            // for (int k = 0; k < characters.length(); k++) {
            // for (int m = 0; m < characters.length(); m++) {
            // for (int n = 0; n < characters.length(); n++) {
            // for (int o = 0; o < characters.length(); o++) {
            // for (int p = 0; p < characters.length(); p++) {
            // String guess = "" + characters.charAt(i) + characters.charAt(j) +
            // characters.charAt(k) + characters.charAt(m) +
            // characters.charAt(n) + characters.charAt(o) + characters.charAt(p);
            // String hashedGuess = md5Hash(guess);

            // if (hashedGuess.equals(TARGET_HASH)) {
            // return guess;
            // }
            // }
            // }
            // }
            // }
            // }
            // }
            // }
            return null;
        }
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, InterruptedException, ExecutionException {
        String characters ="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        // String characters = "0123456789";
        int numThreads = 8; // Number of threads for each process
        int numProcesses = 6; // Number of processes to use for multiprocessing

        List<Future<String>> futures = new ArrayList<>();

        ForkJoinPool forkJoinPool = new ForkJoinPool(numProcesses);
        for (int i = 0; i < numProcesses; i++) {
            int start = i * (characters.length() / numProcesses);
            int end = (i == numProcesses - 1) ? characters.length() : start + (characters.length() / numProcesses);
            ExecutorService executor = Executors.newFixedThreadPool(numThreads);

            CompletionService<String> completionService = new ExecutorCompletionService<>(executor);
            for (int t = 0; t < numThreads; t++) {
                int threadStart = start + (t * ((end - start) / numThreads));
                int threadEnd = (t == numThreads - 1) ? end : start + ((t + 1) * ((end - start) / numThreads));
                BruteForceCallable callable = new BruteForceCallable(characters, threadStart, threadEnd);
                futures.add(completionService.submit(callable));
            }

            executor.shutdown();
        }

        String guessedString = null;
        for (Future<String> future : futures) {
            String result = future.get();
            if (result != null) {
                guessedString = result;
                break;
            }
        }

        forkJoinPool.shutdown();

        if (guessedString != null) {
            System.out.println("Guessed 5-character string: " + guessedString);
        } else {
            System.out.println("No match found.");
        }
    }
}
