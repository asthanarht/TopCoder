package srm476;

import java.util.Arrays;

public class Badgers {
    public int feedMost(int[] hunger, int[] greed, int totalFood) {
        int n = hunger.length;
        int max = 0;
        // test possible number of badgers
        for (int num = 1; num <= n; num++) {
            // food needed if feed with another num-1 badgers
            int[] need = new int[n];
            for (int i = 0; i < n; i++)
                need[i] = hunger[i] + greed[i] * (num - 1);
            // minimum food cost[up to the ith badger][adopted number]
            int[][] dp = new int[n][n + 1];
            for (int i = 0; i < n; i++)
                Arrays.fill(dp[i], -1);
            dp[0][0] = 0; // abandon the 0th badger
            dp[0][1] = need[0]; // feed the oth badger
            for (int i = 0; i < n - 1; i++)
                for (int j = 0; j < n; j++)
                    if (dp[i][j] != -1) {
                        // abandon the (i+1)th badger
                        dp[i + 1][j] = (dp[i + 1][j] == -1 ? dp[i][j] : Math
                                .min(dp[i + 1][j], dp[i][j]));
                        // feed the (i+1)th badger
                        dp[i + 1][j + 1] = (dp[i + 1][j + 1] == -1 ? dp[i][j]
                                + need[i + 1] : Math.min(dp[i + 1][j + 1],
                                dp[i][j] + need[i + 1]));
                    }
            if (dp[n - 1][num] > totalFood)
                break;
            max = num;
        }
        return max;
    }
}