package srm533;

public class CasketOfStar {
   public int maxEnergy(int[] weight) {
      int n = weight.length;
      int[][] dp = new int[n][n]; // best[start][end]
      for (int len = 2; len < n; len++)
         for (int start = 0; start + len < n; start++)
            for (int mid = start + 1; mid < start + len; mid++) {
               int end = start + len;
               dp[start][end] = Math.max(dp[start][end], weight[start]
                     * weight[end] + dp[start][mid] + dp[mid][end]);
            }
      return dp[0][n - 1];
   }
}
