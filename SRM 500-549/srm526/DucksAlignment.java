package srm526;

import java.util.Arrays;

public class DucksAlignment {
   public int minimumTime(String[] grid) {
      int[] rows = new int[grid.length];
      int[] cols = new int[grid[0].length()];
      Arrays.fill(rows, 100);
      Arrays.fill(cols, 100);
      int count = 0;
      for (int i = 0; i < grid.length; i++)
         for (int j = 0; j < grid[i].length(); j++)
            if (grid[i].charAt(j) == 'o') {
               rows[i] = i;
               cols[j] = j;
               count++;
            }
      Arrays.sort(rows);
      Arrays.sort(cols);
      return Math.min(minMove(count, rows) + toOneLine(count, cols),
            minMove(count, cols) + toOneLine(count, rows));
   }

   private int minMove(int count, int[] rows) {
      int min = Integer.MAX_VALUE;
      for (int i = 0; i <= rows.length - count; i++) {
         int move = 0;
         for (int j = 0; j < count; j++)
            move += Math.abs(rows[j] - (i + j));
         min = Math.min(min, move);
      }
      return min;
   }

   private int toOneLine(int count, int[] cols) {
      int min = Integer.MAX_VALUE;
      for (int i = 0; i < cols.length; i++) {
         int sum = 0;
         for (int j = 0; j < count; j++)
            sum += Math.abs(cols[j] - i);
         min = Math.min(min, sum);
      }
      return min;
   }
}