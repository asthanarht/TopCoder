package srm225;

import java.util.Arrays;

public class ComboBoxKeystrokes {
   private int n;
   private int[] chars;

   public int minimumKeystrokes(String[] elements) {
      n = elements.length;
      chars = new int[n];
      for (int i = 0; i < elements.length; i++) {
         char c = elements[i].charAt(0);
         if (Character.isLowerCase(c))
            chars[i] = c - 'a';
         else
            chars[i] = c - 'A';
      }

      int[][] dp = new int[n][n]; // min[start][to]
      for (int i = 0; i < n; i++) {
         Arrays.fill(dp[i], -1);
         dp[i][i] = 0;
      }
      for (int start = 0; start < n; start++) { // start point
         for (int i = 0; i < n; i++) { // current location apart from start
            int cur = (start + i) % n;
            for (int j = 0; j < 26; j++) { // key pressed
               int index = findIndex(cur, j);
               if (index != -1)
                  dp[start][index] = dp[start][index] == -1 ? dp[start][cur] + 1
                        : Math.min(dp[start][index], dp[start][cur] + 1);
            }
         }
      }

      int max = 0;
      for (int i = 0; i < n; i++)
         for (int j = 0; j < n; j++)
            max = Math.max(max, dp[i][j]);
      return max;
   }

   private int findIndex(int cur, int key) {
      for (int i = cur + 1; i < n; i++)
         if (chars[i] == key)
            return i;
      for (int i = 0; i < cur; i++)
         if (chars[i] == key)
            return i;
      return -1;
   }
}
