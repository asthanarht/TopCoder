package srm397;

public class CollectingMarbles {
    public int mostMarbles(int[] marblesWeights, int bagCapacity,
            int numberOfBags) {
        int n = marblesWeights.length;
        int c = bagCapacity + 1;
        int max = 0;
        boolean[][][] dp = new boolean[numberOfBags][c][1 << n];
        dp[0][0][0] = true;
        for (int i = 0; i < numberOfBags; i++)
            for (int j = 0; j < c; j++)
                for (int mask = 0; mask < 1 << n; mask++)
                    if (dp[i][j][mask]) {
                        max = Math.max(max, Integer.bitCount(mask));
                        if (i + 1 < numberOfBags)
                            dp[i + 1][0][mask] = true;
                        for (int m = 0; m < n; m++)
                            if (j + marblesWeights[m] < c)
                                dp[i][j + marblesWeights[m]][mask | 1 << m] = true;
                    }
        return max;
    }
}
