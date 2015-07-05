public class TestBettingStrategy {
    private double res = 0, p, np;
    double[][][] dp = new double[51][1001][11]; // possibility[round][money][lose]
    int g, R;
    int[] bet = new int[10];

    public double winProbability(int initSum, int goalSum, int rounds, int prob) {
        g = goalSum;
        R = rounds;
        p = prob / 100.0;
        np = 1 - p;
        dp[0][initSum][0] = 1;
        bet[0] = 1;
        for (int i = 1; i < 10; i++)
            bet[i] = bet[i - 1] * 2;
        for (int i = 0; i < R; i++)
            for (int k = 0; k < 10; k++)
                for (int j = bet[k]; j < 1001; j++)
                    if (dp[i][j][k] > 0) {
                        if (j + bet[k] >= g)
                            res += dp[i][j][k] * p;
                        else
                            dp[i + 1][j + bet[k]][0] += dp[i][j][k] * p;
                        dp[i + 1][j - bet[k]][k + 1] += dp[i][j][k] * np;
                    }
        return res;
    }
}
