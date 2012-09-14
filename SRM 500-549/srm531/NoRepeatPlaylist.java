package srm531;

public class NoRepeatPlaylist {
   private long[][] dp;
   private int mod = 1000000007;

   public int numPlaylists(int N, int M, int P) {
      dp = new long[P + 1][N + 1]; // permutation[palyed][used]
      dp[0][0] = 1;
      for (int i = 1; i <= P; i++)
         for (int j = 1; j <= N; j++) {
            dp[i][j] += dp[i - 1][j - 1] * (N - j + 1); // play a new
            if (j > M)
               dp[i][j] += dp[i - 1][j] * (j - M); // play an old
            dp[i][j] %= mod;
         }
      return (int) dp[P][N];
   }
}
