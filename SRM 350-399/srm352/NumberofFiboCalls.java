public class NumberofFiboCalls {
    public int[] fiboCallsMade(int n) {
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int i = n; i >= 2; i--) {
            dp[i - 1] += dp[i];
            dp[i - 2] += dp[i];
        }
        int[] res = new int[2];
        if (n == 0)
            res[0] = 1;
        else if (n == 1)
            res[1] = 1;
        else {
            res[0] = dp[0];
            res[1] = dp[1];
        }
        return res;
    }
}
