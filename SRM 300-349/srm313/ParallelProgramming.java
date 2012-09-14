package srm313;

public class ParallelProgramming {
   public int minTime(int[] time, String[] prec) {
      int n = time.length;
      int[] dp = new int[n + 1];
      boolean[] runned = new boolean[n];
      boolean[][] graph = new boolean[n][n];
      for (int i = 0; i < n; i++)
         for (int j = 0; j < n; j++)
            if (prec[i].charAt(j) == 'Y')
               graph[i][j] = true;
            else
               graph[i][j] = false;
      int toRun = n;
      while (toRun > 0) {
         boolean done = false;
         for (int j = 0; j < n; j++)
            if (!runned[j]) {
               boolean couldRun = true;
               for (int i = 0; i < n; i++)
                  if (graph[i][j]) {
                     couldRun = false;
                     break;
                  }
               if (couldRun) {
                  int amount = dp[j] + time[j];
                  boolean hasNext = false;
                  for (int k = 0; k < n; k++)
                     if (graph[j][k]) {
                        hasNext = true;
                        if (amount > dp[k])
                           dp[k] = amount;
                        graph[j][k] = false;
                     }
                  if (!hasNext)
                     dp[n] = Math.max(dp[n], amount);
                  runned[j] = true;
                  toRun--;
                  done = true;
                  break;
               }
            }
         if (!done)
            return -1;
      }
      return dp[n];
   }
}
