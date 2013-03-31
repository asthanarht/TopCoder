package r2A;

public class TheLargestString {
    public static String find(String s, String t) {
        int n = s.length();
        String[][] dp1 = new String[n][n + 1];
        String[][] dp2 = new String[n][n + 1];
        for (int i = 0; i < n; i++) {
            dp1[i][1] = "" + s.charAt(i);
            dp2[i][1] = "" + t.charAt(i);
        }
        for (int index = 0; index < n; index++)
            for (int len = 1; len <= n; len++)
                if (dp1[index][len] != null) {
                    for (int i = index + 1; i < n; i++) {
                        String w1 = dp1[index][len] + s.charAt(i);
                        String w2 = dp2[index][len] + t.charAt(i);
                        if (dp1[i][len + 1] == null
                                || (w1 + w2).compareTo(dp1[i][len + 1]
                                        + dp2[i][len + 1]) > 0) {
                            dp1[i][len + 1] = w1;
                            dp2[i][len + 1] = w2;
                        }
                    }
                }
        String max = "";
        for (int index = 0; index < n; index++)
            for (int len = 1; len <= n; len++)
                if (dp1[index][len] != null) {
                    String word = dp1[index][len] + dp2[index][len];
                    if (word.compareTo(max) > 0)
                        max = word;
                }
        return max;
    }
}
