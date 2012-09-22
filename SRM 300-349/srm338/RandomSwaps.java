package srm338;

public class RandomSwaps {
    public double getProbability(int arrayLength, int swapCount, int a, int b) {
        int n = arrayLength, m = swapCount, t = (n * (n - 1)) >> 1;
        double pa2c = (n - 1.0) / t;
        double pa2a = 1 - pa2c;
        double[] dp = new double[m + 1];
        dp[0] = 1;
        for (int i = 0; i < m; i++)
            dp[i + 1] += dp[i] * pa2a + (1 - dp[i]) * pa2c / (n - 1);
        if (b == a)
            return dp[m];
        return (1 - dp[m]) / (n - 1);
    }
}
