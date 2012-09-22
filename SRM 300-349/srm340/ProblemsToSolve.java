package srm340;

public class ProblemsToSolve {
    public int minNumber(int[] pleasantness, int variety) {
        int n = pleasantness.length;
        int[] p = pleasantness;
        byte[][][] dp = new byte[n][1001][1001]; // count[to][min][max]
        dp[0][p[0]][p[0]] = 1;
        for (int i = 0; i < n; i++)
            for (int min = 0; min <= 1000; min++)
                for (int max = min; max <= 1000; max++)
                    if (dp[i][min][max] > 0) {
                        if (i + 1 < n) {
                            if (p[i + 1] > max) {
                                int b = Math.max(max, p[i + 1]);
                                if (dp[i + 1][min][b] == 0
                                        || dp[i + 1][min][b] > dp[i][min][max] + 1)
                                    dp[i + 1][min][b] = (byte) (dp[i][min][max] + 1);
                            }
                            else if (p[i + 1] < min) {
                                int a = Math.min(min, p[i + 1]);
                                if (dp[i + 1][a][max] == 0
                                        || dp[i + 1][a][max] > dp[i][min][max] + 1)
                                    dp[i + 1][a][max] = (byte) (dp[i][min][max] + 1);
                            }
                            else if (dp[i + 1][min][max] == 0
                                    || dp[i + 1][min][max] > dp[i][min][max] + 1)
                                dp[i + 1][min][max] = (byte) (dp[i][min][max] + 1);
                        }
                        if (i + 2 < n) {
                            if (p[i + 2] > max) {
                                int b = Math.max(max, p[i + 2]);
                                if (dp[i + 2][min][b] == 0
                                        || dp[i + 2][min][b] > dp[i][min][max] + 1)
                                    dp[i + 2][min][b] = (byte) (dp[i][min][max] + 1);
                            }
                            else if (p[i + 2] < min) {
                                int a = Math.min(min, p[i + 2]);
                                if (dp[i + 2][a][max] == 0
                                        || dp[i + 2][a][max] > dp[i][min][max] + 1)
                                    dp[i + 2][a][max] = (byte) (dp[i][min][max] + 1);
                            }
                            else if (dp[i + 2][min][max] == 0
                                    || dp[i + 2][min][max] > dp[i][min][max] + 1)
                                dp[i + 2][min][max] = (byte) (dp[i][min][max] + 1);
                        }
                    }
        int res = n;
        for (int i = 1; i < n; i++)
            for (int min = 0; min <= 1000; min++)
                for (int max = min + variety; max <= 1000; max++)
                    if (dp[i][min][max] > 0)
                        res = Math.min(res, dp[i][min][max]);
        return res;
    }
}
