public class PrimeSoccer {
    public double getProbability(int skillOfTeamA, int skillOfTeamB) {
        double[] p = { skillOfTeamA / 100d, skillOfTeamB / 100d };
        int n = 90 / 5;
        double[][][] dp = new double[2][n + 1][n + 1];
        dp[0][0][0] = 1;
        dp[1][0][0] = 1;
        for (int t = 0; t < 2; t++)
            for (int i = 1; i <= n; i++)
                for (int j = 0; j <= n; j++) {
                    dp[t][i][j] = dp[t][i - 1][j] * (1 - p[t]);
                    if (j > 0)
                        dp[t][i][j] += dp[t][i - 1][j - 1] * p[t];
                }
        double result = 0;
        int[] prime = { 2, 3, 5, 7, 11, 13, 17 };
        for (int i = 0; i < prime.length; i++)
            result += dp[0][n][prime[i]] + dp[1][n][prime[i]];
        for (int i = 0; i < prime.length; i++)
            for (int j = 0; j < prime.length; j++)
                result -= dp[0][n][prime[i]] * dp[1][n][prime[j]];
        return result;
    }
}
