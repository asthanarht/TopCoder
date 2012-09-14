package srm425;

public class CrazyBot {
   boolean[][] visited;
   double[] p;
   int[] x = { 1, -1, 0, 0 };
   int[] y = { 0, 0, -1, 1 };

   public double getProbability(int n, int east, int west, int south, int north) {
      visited = new boolean[n * 2 + 1][n * 2 + 1];
      p = new double[] { east * 0.01, west * 0.01, south * 0.01, north * 0.01 };
      return dfs(n, n, n);
   }

   private double dfs(int togo, int r, int c) {
      if (togo == 0)
         return 1;
      double result = 0;
      visited[r][c] = true;
      for (int i = 0; i < 4; i++) {
         int nr = r + x[i];
         int nc = c + y[i];
         if (!visited[nr][nc])
            result += p[i] * dfs(togo - 1, nr, nc);
      }
      visited[r][c] = false;
      return result;
   }
}
