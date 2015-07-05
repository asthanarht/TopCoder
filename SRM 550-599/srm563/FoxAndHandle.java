public class FoxAndHandle {
    public String lexSmallestName(String S) {
        int n = S.length();
        String[][] dp = new String[n][n + 1]; // min[after position][length]
        dp[0][0] = "";
        dp[0][1] = S.substring(0, 1);
        for (int pos = 0; pos < n - 1; pos++)
            for (int len = 0; len <= pos + 1; len++)
                if (dp[pos][len] != null) {
                    // skip next
                    if (check(S, dp[pos][len], pos + 1))
                        dp[pos + 1][len] = min(dp[pos + 1][len], dp[pos][len]);
                    // not skip next
                    String newString = dp[pos][len] + S.charAt(pos + 1);
                    if (check(S, newString, pos + 1))
                        dp[pos + 1][len + 1] = min(dp[pos + 1][len + 1],
                                newString);
                }
        return dp[n - 1][n / 2];
    }

    private boolean check(String S, String s, int pos) {
        int[] expect = new int[26];
        int[] count = new int[26];
        for (int i = 0; i < S.length(); i++)
            expect[S.charAt(i) - 'a']++;
        for (int i = 0; i < 26; i++)
            expect[i] /= 2;
        for (int i = 0; i < s.length(); i++)
            count[s.charAt(i) - 'a']++;
        for (int i = 0; i < 26; i++)
            if (count[i] > expect[i])
                return false;
        for (int i = pos + 1; i < S.length(); i++)
            count[S.charAt(i) - 'a']++;
        for (int i = 0; i < 26; i++)
            if (count[i] < expect[i])
                return false;
        return true;
    }

    private String min(String s1, String s2) {
        if (s1 == null || s1.length() == 0)
            return s2;
        if (s1.compareTo(s2) <= 0)
            return s1;
        return s2;
    }
}
