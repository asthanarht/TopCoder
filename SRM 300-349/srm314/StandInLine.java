package srm314;

import java.util.Arrays;

public class StandInLine {
   public int[] reconstruct(int[] left) {
      int n = left.length;
      int[] result = new int[n];
      Arrays.fill(result, -1);
      for (int i = 0; i < n; i++) {
         int tall = left[i];
         for (int j = 0; j < n; j++)
            if (tall > 0) {
               if (result[j] == -1)
                  tall--;
            }
            else if (tall == 0) {
               if (result[j] == -1) {
                  result[j] = i + 1;
                  break;
               }
            }
            else
               break;
      }
      return result;
   }
}
