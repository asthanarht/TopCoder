package srm311;

import java.util.Arrays;

public class MatrixTransforming {
   public int minPushes(String[] a, String[] b) {
      int m = a.length, n = a[0].length();
      boolean[][] A = new boolean[m][n];
      boolean[][] B = new boolean[m][n];
      for (int i = 0; i < m; i++)
         for (int j = 0; j < n; j++) {
            A[i][j] = a[i].charAt(j) == '1' ? true : false;
            B[i][j] = b[i].charAt(j) == '1' ? true : false;
         }

      int count = 0;
      for (int i = 0; i < m - 2; i++)
         for (int j = 0; j < n - 2; j++)
            if (A[i][j] != B[i][j]) {
               count++;
               for (int x = 0; x < 3; x++)
                  for (int y = 0; y < 3; y++)
                     A[i + x][j + y] = !A[i + x][j + y];
            }

      for (int i = 0; i < m; i++)
         if (!Arrays.equals(A[i], B[i]))
            return -1;
      return count;
   }
}
