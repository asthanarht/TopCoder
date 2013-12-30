package srm602;

import java.util.Arrays;

public class TypoCoderDiv1 {
    public int getmax(int[] D, int X) {
        int n = D.length;
        int[][] dp = new int[n + 1][2201];
        for (int i = 0; i <= n; i++)
            Arrays.fill(dp[i], -1);
        dp[0][X] = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < 2200; j++)
                if (dp[i][j] != -1) {
                    dp[i + 1][Math.max(0, j - D[i])] = Math.max(
                            dp[i + 1][Math.max(0, j - D[i])], dp[i][j]);
                    if (j + D[i] >= 2200) {
                        if (i + 1 == n)
                            dp[n][2200] = Math.max(dp[n][2200], dp[i][j] + 1);
                        else if (j + D[i] - D[i + 1] < 2200)
                            dp[i + 2][Math.max(0, j + D[i] - D[i + 1])] = Math
                                    .max(dp[i + 2][Math.max(0, j + D[i]
                                            - D[i + 1])], dp[i][j] + 2);
                    }
                    else
                        dp[i + 1][j + D[i]] = Math.max(dp[i + 1][j + D[i]],
                                dp[i][j]);
                }
        int res = 0;
        for (int i = 0; i <= 2200; i++)
            res = Math.max(res, dp[n][i]);
        return res;
    }
}
