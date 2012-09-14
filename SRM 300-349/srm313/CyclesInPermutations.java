package srm313;

import java.util.Arrays;

public class CyclesInPermutations {
   public int maxCycle(int[] board) {
      int[] count = new int[board.length];
      Arrays.fill(count, 1);
      for (int i = 0; i < board.length; i++) {
         int index = i;
         do {
            count[i]++;
            index = board[index] - 1;
         } while (index != i);
      }
      int max = 0;
      for (int i = 0; i < board.length; i++)
         max = Math.max(max, count[i]);
      return max;
   }
}
