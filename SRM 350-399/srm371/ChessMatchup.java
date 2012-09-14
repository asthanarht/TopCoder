package srm371;

import java.util.Arrays;

public class ChessMatchup {
   public int maximumScore(int[] us, int[] them) {
      Arrays.sort(us);
      Arrays.sort(them);
      int max = 0;
      int n = us.length;
      for (int i = 0; i < n; i++) {
         int score = 0;
         for (int j = 0; j < n; j++) {
            int k = (i + j) % n;
            if (us[k] > them[j])
               score += 2;
            else if (us[k] == them[j])
               score += 1;
         }
         max = Math.max(max, score);
      }
      return max;
   }
}
