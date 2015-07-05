import java.util.Arrays;

public class BalanceScale {
    /*
     * ax + by = c there exists integer sulotion for x, y when c % gcd(a,b) == 0
     */
    public int takeWeights(int[] weight) {
        if (weight.length == 1)
            return 1;
        int gcd = (int) gcd(weight[0], weight[1]);
        for (int i = 2; i < weight.length; i++)
            gcd = (int) gcd(gcd, weight[i]);
        for (int i = 0; i < weight.length; i++)
            weight[i] /= gcd;
        Arrays.sort(weight);
        int[] dp = new int[10000001]; // min[gcd]
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (int i = 0; i < weight.length; i++)
            dp[weight[i]] = 1;
        // Adding one more number can only make gcd to be smaller.
        for (int i = 10000000; i > 1; i--)
            if (dp[i] != Integer.MAX_VALUE)
                for (int j = 0; j < weight.length; j++) {
                    int g = (int) gcd(i, weight[j]);
                    dp[g] = Math.min(dp[g], dp[i] + 1);
                }
        return dp[1];
    }

    private int gcd(int x, int y) {
        int hold;
        while (y != 0) {
            hold = x % y;
            x = y;
            y = hold;
        }
        return x;
    }
}
