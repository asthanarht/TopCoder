package srm353;

import java.util.Arrays;

public class PlatformJumper {
   public int maxCoins(String[] platforms, int v, int g) {
      int n = platforms.length;
      Platform[] pfs = new Platform[n];
      for (int i = 0; i < n; i++) {
         String[] info = platforms[i].split(" ");
         pfs[i] = new Platform(Integer.parseInt(info[0]),
               Integer.parseInt(info[1]), Integer.parseInt(info[2]));
      }
      Arrays.sort(pfs);
      int[] dp = new int[platforms.length];
      int max = 0;
      for (int i = 0; i < n; i++) {
         dp[i] = pfs[i].c;
         for (int j = 0; j < i; j++)
            if (dp[j] + pfs[i].c > dp[i]
                  && g * (long) (pfs[j].x - pfs[i].x) * (pfs[j].x - pfs[i].x) <= 2
                        * (long) (pfs[i].y - pfs[j].y) * v * v)
               dp[i] = dp[j] + pfs[i].c;
         max = Math.max(max, dp[i]);
      }
      return max;
   }
}

class Platform implements Comparable<Object> {
   int x;
   int y;
   int c;

   public Platform(int x, int y, int c) {
      super();
      this.x = x;
      this.y = y;
      this.c = c;
   }

   public int compareTo(Object arg0) {
      return y - ((Platform) arg0).y;
   }
}
