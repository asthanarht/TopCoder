package srm565;

public class MonstersValley {
    public int minimumPrice(long[] dread, int[] price) {
        int n = dread.length, max = n + n;
        long[][] dp = new long[n][max + 1]; // dread[after position n][paid]
        dp[0][price[0]] = dread[0];
        for (int i = 0; i < n - 1; i++)
            for (int j = 1; j < max; j++)
                if (dp[i][j] > 0) {
                    if (dp[i][j] >= dread[i + 1])
                        dp[i + 1][j] = Math.max(dp[i + 1][j], dp[i][j]);
                    int pay = j + price[i + 1];
                    dp[i + 1][pay] = Math.max(dp[i + 1][pay], dp[i][j]
                            + dread[i + 1]);
                }
        for (int j = 1; j < max; j++)
            if (dp[n - 1][j] > 0)
                return j;
        return max;
    }
}
