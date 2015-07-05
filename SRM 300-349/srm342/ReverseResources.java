public class ReverseResources {
    private int[][] dp;
    private int mod = 1000000007;

    public int findDecompositions(String str, String[] resources) {
        int n = str.length(), m = resources.length;
        for (int i = 0; i < m; i++)
            resources[i] = resources[i].replaceAll("%s", "*");
        dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--)
            for (int j = i; j < n; j++)
                for (int k = 0; k < m; k++) {
                    dp[i][j] += match(str.substring(i, j + 1), resources[k], i);
                    dp[i][j] %= mod;
                }
        return dp[0][n - 1];
    }

    private int match(String s, String re, int start) {
        int n = s.length(), m = re.length();
        if (m > n)
            return 0;
        int[][] ddp = new int[n + 1][m + 1];
        ddp[n][m] = 1;
        for (int i = n - 1; i >= 0; i--)
            for (int j = m - 1; j >= 0; j--)
                if (re.charAt(j) == '*') {
                    for (int k = i; k < n; k++) {
                        ddp[i][j] += (int) ((long) ddp[k + 1][j + 1]
                                * dp[start + i][start + k] % mod);
                        ddp[i][j] %= mod;
                    }
                }
                else if (s.charAt(i) == re.charAt(j)) {
                    ddp[i][j] += ddp[i + 1][j + 1];
                    ddp[i][j] %= mod;
                }
        return ddp[0][0];
    }
}