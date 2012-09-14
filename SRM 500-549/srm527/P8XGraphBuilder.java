package srm527;

public class P8XGraphBuilder {
   public int solve(int[] scores) {
      int n = scores.length;
      int[][] dp = new int[n + 1][n * 2 + 1]; // maxScore[nodeUsed][edgeUsed]
      for (int i = 0; i <= n; i++) {
         int usedmin = i;
         int usedmax = Math.min(i * n, n * 2 - (n - i + 1));
         for (int used = usedmin; used <= usedmax; used++) {
            int min = 1;
            int max = Math.min(n, n * 2 - used - (n - i));
            for (int use = min; use <= max; use++) {
               int dpitotal = scores[use - 1];
               dpitotal += i == 0 ? 0 : dp[i - 1][used];
               int total = used + use;
               dp[i][total] = Math.max(dp[i][total], dpitotal);
            }
         }
      }
      return dp[n][n * 2];
   }
}