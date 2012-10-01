package srm401;

public class FIELDDiagrams {
    public long countDiagrams(int fieldOrder) {
        int n = fieldOrder;
        long[][] dp = new long[n][n + 1]; // dp[n-k][ak]
        dp[0][0] = 1;
        dp[0][1] = 1;
        for (int k = 0, max = 2; k < n - 1; k++, max++)
            for (int ak2 = 0; ak2 < max; ak2++)
                for (int ak1 = ak2; ak1 <= max; ak1++)
                    dp[k + 1][ak1] += dp[k][ak2];
        long result = 0;
        // ak==0 means all rows equal to 0
        for (int ak = 1; ak <= n; ak++)
            result += dp[n - 1][ak];
        return result;
    }
}
