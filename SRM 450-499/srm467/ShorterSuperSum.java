public class ShorterSuperSum {
    public int calculate(int k, int n) {
        int[][] dp = new int[k + 1][n + 1];
        for (int j = 1; j <= n; j++)
            dp[0][j] = j;
        for (int i = 1; i <= k; i++)
            for (int j = 1; j <= n; j++)
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
        return dp[k][n];
    }
}
