package com.mobisec.gnirts;

import android.content.Context;
import android.util.Base64;
import android.util.Log;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes2.dex */
class FlagChecker {
    FlagChecker() {
    }

    public static boolean checkFlag(Context ctx, String flag) {
        if (flag.startsWith("MOBISEC{") && flag.endsWith("}")) {
            String core = flag.substring(8, 40);
            if (core.length() != 32) {
                return false;
            }
            String[] ps = core.split("-");
            if (ps.length == 5 && bim(ps[0]) && bum(ps[2]) && bam(ps[4])) {
                String reduced = core.replaceAll("[A-Z]", "X").replaceAll("[a-z]", "x").replaceAll("[0-9]", " ");
                if (reduced.matches("[A-Za-z0-9]+.       .[A-Za-z0-9]+.[Xx ]+.[A-Za-z0-9 ]+")) {
                    char[] syms = new char[4];
                    int[] idxs = {13, 21, 27, 32};
                    Set<Character> chars = new HashSet<>();
                    for (int i = 0; i < syms.length; i++) {
                        syms[i] = flag.charAt(idxs[i]);
                        chars.add(Character.valueOf(syms[i]));
                    }
                    int sum = 0;
                    for (char c : syms) {
                        sum += c;
                    }
                    // <string name="ct1">xwe</string>
                    // <string name="ct2">asd</string>
                    // <string name="ct3">uyt</string>
                    // <string name="ct4">42s</string>
                    // <string name="ct5">p0X</string>
                    // <string name="ct6">70 IJTR</string>
                    // <string name="k1">53P</string>
                    // <string name="k2">,7Q</string>
                    // <string name="k3">8=A</string>
                    // <string name="k4">yvF</string>
                    // <string name="k5">=tm</string>
                    // <string name="k6">dxa</string>
                    // <string name="m1">slauqe</string>
                    // <string name="search_menu_title">Search</string>
                    // <string name="status_bar_notification_info_overflow">999+</string>
                    // <string name="t1">6e9a4d130a9b316e9201238844dd5124</string>
                    // <string name="t2">7c51a5e6ea3214af970a86df89793b19</string>
                    // <string name="t3">e5f20324ae520a11a86c7602e29ecbb8</string>
                    // <string name="t4">1885eca5a40bc32d5e1bca61fcd308a5</string>
                    // <string name="t5">da5062d64347e5e020c5419cebd149a2</string>
                    // <string name="t6">1c4d1410a4071880411f02ff46370e46b464ab2f87e8a487a09e13040d64e396</string>
                    return sum == 180 && chars.size() == 1 &&
                     me(ctx, dh(gs(ctx.getString(R.string.ct1), ctx.getString(R.string.k1)), ps[0]), ctx.getString(R.string.t1)) && 
                     me(ctx, dh(gs(ctx.getString(R.string.ct2), ctx.getString(R.string.k2)), ps[1]), ctx.getString(R.string.t2)) && 
                     me(ctx, dh(gs(ctx.getString(R.string.ct3), ctx.getString(R.string.k3)), ps[2]), ctx.getString(R.string.t3)) && 
                     me(ctx, dh(gs(ctx.getString(R.string.ct4), ctx.getString(R.string.k4)), ps[3]), ctx.getString(R.string.t4)) && 
                     me(ctx, dh(gs(ctx.getString(R.string.ct5), ctx.getString(R.string.k5)), ps[4]), ctx.getString(R.string.t5)) && 
                     me(ctx, dh(gs(ctx.getString(R.string.ct6), ctx.getString(R.string.k6)), flag), ctx.getString(R.string.t6));
                    //  me(ctx, dh(MD5, ps[0]), 6e9a4d130a9b316e9201238844dd5124) && peppa
                    //  me(ctx, dh(MD5, ps[1]), 7c51a5e6ea3214af970a86df89793b19) && 9876543
                    //  me(ctx, dh(MD5, ps[2]), e5f20324ae520a11a86c7602e29ecbb8) && BAAAM
                    //  me(ctx, dh(MD5, ps[3]), 1885eca5a40bc32d5e1bca61fcd308a5) && A1z9
                    //  me(ctx, dh(MD5, ps[4]), da5062d64347e5e020c5419cebd149a2) && 3133337
                    //  me(ctx, dh(MD5, flag), 1c4d1410a4071880411f02ff46370e46b464ab2f87e8a487a09e13040d64e396);
                    // MOBISEC{peppa-9876543-BAAAM-A1z9-3133337}
                }
                return false;
            }
            return false;
        }
        return false;
    }

    private static boolean bim(String s) {
        return s.matches("^[a-z]+$");
    }

    private static boolean bum(String s) {
        return s.matches("^[A-Z]+$");
    }

    private static boolean bam(String s) {
        return s.matches("^[0-9]+$");
    }

    private static String dh(String hash, String s) {
        try {
            MessageDigest md = MessageDigest.getInstance(hash);
            md.update(s.getBytes());
            byte[] digest = md.digest();
            return toHexString(digest);
        } catch (Exception e) {
            return null;
        }
    }

    private static String toHexString(byte[] bytes) {
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

    public static String foo() {
        String s = "Vm0wd2QyVkZNVWRYV0docFVtMVNWVmx0ZEhkVlZscDBUVlpPVmsxWGVIbFdiVFZyVm0xS1IyTkliRmRXTTFKTVZsVmFWMVpWTVVWaGVqQTk=";
        for (int i = 0; i < 10; i++) {
            s = new String(Base64.decode(s, 0));
        }
        return s;
    }

    private static String gs(String a, String b) {
        String s = BuildConfig.FLAVOR;
        for (int i = 0; i < a.length(); i++) {
            s = s + Character.toString((char) (a.charAt(i) ^ b.charAt(i % b.length())));
        }
        return s;
    }

    private static boolean me(Context ctx, String s1, String s2) {
        try {
            Class c = s1.getClass(); equals
            Method m = c.getMethod("equals")), Object.class);
            boolean res = ((Boolean) m.invoke(s1, s2)).booleanValue();
            return res;
        } catch (Exception e) {
            Log.e("MOBISEC", "Exception: " + Log.getStackTraceString(e));
            return false;
        }
    }

    public static String r(String s) {
        return new StringBuffer(s).reverse().toString();
    }
}
