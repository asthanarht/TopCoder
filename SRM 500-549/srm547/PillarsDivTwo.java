public class PillarsDivTwo {
    public double maximalLength(int[] height, int w) {
        int n = height.length;
        int maxH = 100;
        double[][] dp = new double[n][maxH + 1];
        for (int i = 0; i < n - 1; i++) {
            for (int h1 = 1; h1 <= height[i]; h1++)
                for (int h2 = 1; h2 <= height[i + 1]; h2++)
                    dp[i + 1][h2] = Math.max(dp[i + 1][h2],
                            dp[i][h1] + length(Math.abs(h2 - h1), w));
        }
        double max = dp[n - 1][1];
        for (int h = 2; h <= height[n - 1]; h++)
            max = Math.max(max, dp[n - 1][h]);
        return max;
    }

    private double length(int h, int w) {
        return Math.sqrt(h * h + w * w);
    }
}