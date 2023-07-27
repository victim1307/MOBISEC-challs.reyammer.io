package com.mobisec.blockchain;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.MessageDigest;
import java.util.ArrayList;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;

import android.support.asynclayoutinflater.R.string;
/* loaded from: classes2.dex */
class FlagChecker {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    FlagChecker() {
    }

    public static boolean checkFlag(String keyStr, String flagStr) throws Exception {
        byte[] fullKey = keyStr.getBytes();
        byte[] digest = hash(fullKey);
        byte[] key = {digest[0], digest[digest.length / 2], digest[digest.length - 1]};
        byte[] currKey = hash(key);
        byte[] currPt = flagStr.getBytes();
        for (int i = 0; i < 10; i++) {
            byte[] newPt = encrypt(currPt, currKey);
            currPt = newPt;
            currKey = hash(currKey);
        }
        return toHex(currPt).equals("0eef68c5ef95b67428c178f045e6fc8389b36a67bbbd800148f7c285f938a24e696ee2925e12ecf7c11f35a345a2a142639fe87ab2dd7530b29db87ca71ffda2af558131d7da615b6966fb0360d5823b79c26608772580bf14558e6b7500183ed7dfd41dbb5686ea92111667fd1eff9cec8dc29f0cfe01e092607da9f7c2602f5463a361ce5c83922cb6c3f5b872dcc088eb85df80503c92232bf03feed304d669ddd5ed1992a26674ecf2513ab25c20f95a5db49fdf6167fda3465a74e0418b2ea99eb2673d4c7e1ff7c4921c4e2d7b");
    }

     public static byte[] decrypt(byte[] in, byte[] key) throws Exception {
        Key aesKey = new SecretKeySpec(key, "AES");
        Cipher decryptCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        decryptCipher.init(Cipher.DECRYPT_MODE, aesKey);

        ByteArrayInputStream inputStream = new ByteArrayInputStream(in);
        CipherInputStream cipherInputStream = new CipherInputStream(inputStream, decryptCipher);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = cipherInputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        cipherInputStream.close();
        outputStream.close();

        return outputStream.toByteArray();
    }

    public static byte[] decryptall(byte[] in, byte[] key) throws Exception {
        // make a list of all the possible keys
         ArrayList<byte[]> keyList = new ArrayList<>();
         byte[] hash = hash(key);
         for (int i = 0; i < 9; i++) {
            keyList.add(hash);
            hash = hash(hash);
         }

         // decrypt the flag with each key
         byte[] decryptFlag = decrypt(in, keyList.get(9));
         for (int i = 0; i > -1; i--) {
            in = decryptFlag;
            decryptFlag = decrypt(in, keyList.get(i));
         }

         return decryptFlag;

    }


    public static byte[] brute() throws Exception {
        for (int i = -128; i < 128; i++) {
            for (int j = -128; j < 128; j++) {
                for (int k = -128; k < 128; k++) {
                    byte[] key = {(byte) i, (byte) j, (byte) k};

                    try {
                        // Attempt decryption with the current key
                        byte[] decryptedData = decryptall(hexStringToByteArray("0eef68c5ef95b67428c178f045e6fc8389b36a67bbbd800148f7c285f938a24e696ee2925e12ecf7c11f35a345a2a142639fe87ab2dd7530b29db87ca71ffda2af558131d7da615b6966fb0360d5823b79c26608772580bf14558e6b7500183ed7dfd41dbb5686ea92111667fd1eff9cec8dc29f0cfe01e092607da9f7c2602f5463a361ce5c83922cb6c3f5b872dcc088eb85df80503c92232bf03feed304d669ddd5ed1992a26674ecf2513ab25c20f95a5db49fdf6167fda3465a74e0418b2ea99eb2673d4c7e1ff7c4921c4e2d7b"), key);
    
                        // Check if the decrypted data matches the expected flag
                        String decryptedFlag = new String(decryptedData);
                        if (decryptedFlag.startsWith("MOBISEC")) {
                            return key;
                        }
                    } catch (Exception e) {
                        // If decryption fails, continue to the next key
                        continue;
                    }
                }
            }
        }
        return null; // If the correct key is not found, return null
    }
    
    // Helper method to convert a hexadecimal string to a byte array
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                               + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
    

    public static byte[] encrypt(byte[] in, byte[] key) throws Exception {
        Key aesKey = new SecretKeySpec(key, "AES");
        Cipher encryptCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        encryptCipher.init(1, aesKey);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, encryptCipher);
        cipherOutputStream.write(in);
        cipherOutputStream.flush();
        cipherOutputStream.close();
        byte[] out = outputStream.toByteArray();
        return out;
    }

    public static byte[] hash(byte[] in) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(in);
        return md.digest();
    }

    public static String toHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(b & 255);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
