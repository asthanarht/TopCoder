package srm564;

public class AlternateColors2 {
    public long countWays(int n, int k) {
        // ways[ith ball][i-2th color][i-1th color][ith color]
        long[][][][] dp = new long[n + 1][3][3][3];
        dp[1][1][2][0] = 1;
        for (int i = 1; i < n; i++)
            for (int pre2 = 0; pre2 < 3; pre2++)
                for (int pre1 = 0; pre1 < 3; pre1++)
                    for (int cur = 0; cur < 3; cur++) {
                        if (i == k && cur != 0)
                            continue;
                        if (pre1 == cur)
                            dp[i + 1][pre1][cur][cur] += dp[i][pre2][pre1][cur];
                        else if (pre2 == cur) {
                            dp[i + 1][pre1][cur][cur] += dp[i][pre2][pre1][cur];
                            dp[i + 1][pre1][cur][pre1] += dp[i][pre2][pre1][cur];
                        }
                        else if (pre2 != pre1 && pre1 != cur && cur != pre2) {
                            dp[i + 1][pre1][cur][cur] += dp[i][pre2][pre1][cur];
                            dp[i + 1][pre1][cur][pre1] += dp[i][pre2][pre1][cur];
                            dp[i + 1][pre1][cur][pre2] += dp[i][pre2][pre1][cur];
                        }
                    }

        long result = 0;
        for (int pre2 = 0; pre2 < 3; pre2++)
            for (int pre1 = 0; pre1 < 3; pre1++)
                for (int cur = 0; cur < 3; cur++)
                    if (n != k || cur == 0) // necessary when k==n
                        result += dp[n][pre2][pre1][cur];
        return result;
    }
}
