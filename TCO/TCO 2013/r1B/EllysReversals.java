package r1B;

import java.util.Arrays;

public class EllysReversals {
    public int getMin(String[] words) {
        int n = words.length;
        boolean[] tick = new boolean[n];
        int result = n;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                if (!tick[i] && !tick[j] && check(words[i], words[j])) {
                    tick[i] = true;
                    tick[j] = true;
                    result -= 2;
                }
        return result;
    }

    private boolean check(String w1, String w2) {
        if (w1.length() != w2.length())
            return false;
        int n = w1.length();
        if (n % 2 == 1 && w1.charAt(n - 1) != w2.charAt(n - 1))
            return false;
        String[] parts1 = new String[n / 2];
        for (int i = 0; i + 1 < w1.length(); i += 2) {
            char c1 = w1.charAt(i);
            char c2 = w1.charAt(i + 1);
            String s = "";
            if (c1 < c2) {
                s += c1;
                s += c2;
            }
            else {
                s += c2;
                s += c1;
            }
            parts1[i / 2] = s;
        }
        String[] parts2 = new String[n / 2];
        for (int i = 0; i + 1 < w2.length(); i += 2) {
            char c1 = w2.charAt(i);
            char c2 = w2.charAt(i + 1);
            String s = "";
            if (c1 < c2) {
                s += c1;
                s += c2;
            }
            else {
                s += c2;
                s += c1;
            }
            parts2[i / 2] = s;
        }
        Arrays.sort(parts1);
        Arrays.sort(parts2);
        for (int i = 0; i < w1.length() / 2; i++)
            if (parts1[i].compareTo(parts2[i]) != 0)
                return false;
        return true;
    }
}
