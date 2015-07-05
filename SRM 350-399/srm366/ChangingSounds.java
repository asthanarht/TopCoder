public class ChangingSounds {
    public int maxFinal(int[] changeIntervals, int beginLevel, int maxLevel) {
        int n = changeIntervals.length, m = maxLevel + 1;
        boolean[][] dp = new boolean[n + 1][m]; // achievable[song][level]
        dp[0][beginLevel] = true;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (dp[i][j]) {
                    if (j + changeIntervals[i] < m)
                        dp[i + 1][j + changeIntervals[i]] = true;
                    if (j - changeIntervals[i] >= 0)
                        dp[i + 1][j - changeIntervals[i]] = true;
                }
        for (int i = m - 1; i >= 0; i--)
            if (dp[n][i])
                return i;
        return -1;
    }
}
