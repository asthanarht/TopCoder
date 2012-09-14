package srm500;

import java.util.Arrays;

public class SRMCards {
   public int maxTurns(int[] cards) {
      int count = 0;
      int n = cards.length;
      Arrays.sort(cards);
      for (int i = 0; i < n; i++, count++) {
         if (i + 1 < n && cards[i + 1] == cards[i] + 1)
            i++;
      }
      return count;
   }
}
