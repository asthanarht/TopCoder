public class CollectingPayment {
    public double maximumProfit(int[] earning, int[] moment, int fee, int rate) {
        int n = earning.length;
        double[] dp = new double[n + 1]; // best[n]
        // last cash out after earning[i-1]
        for (int i = n - 1; i >= 0; i--)
            // cash out after earning[j]
            for (int j = i, amount = 0; j < n; j++) {
                amount += earning[j]; // accumulated amount
                double deposite = amount - fee;
                if (deposite > 0) {
                    for (int k = moment[j]; k <= 365; k += 7)
                        deposite += deposite * rate / 1000;
                    dp[i] = Math.max(dp[i], dp[j + 1] + deposite);
                }
            }
        return dp[0];
    }
}
