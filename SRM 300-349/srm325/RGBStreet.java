package srm325;

public class RGBStreet {
    public int estimateCost(String[] houses) {
        int n = houses.length;
        int[][] cost = new int[n][3];
        for (int i = 0; i < n; i++) {
            String[] info = houses[i].split(" ");
            for (int j = 0; j < 3; j++)
                cost[i][j] = Integer.parseInt(info[j]);
        }
        int[][] dp = new int[n][3]; // min[to][color]
        for (int i = 0; i < 3; i++)
            dp[0][i] = cost[0][i];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 3; j++)
                dp[i][j] = Integer.MAX_VALUE;
            for (int j = 0; j < 3; j++)
                for (int k = 0; k < 3; k++)
                    if (j != k)
                        dp[i][j] = Math
                                .min(dp[i][j], dp[i - 1][k] + cost[i][j]);
        }
        int min = Math.min(dp[n - 1][0], dp[n - 1][1]);
        return Math.min(min, dp[n - 1][2]);
    }
}
