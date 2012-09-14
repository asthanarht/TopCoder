package srm309;

import java.util.Arrays;

public class ContestCoordinator {
   public double bestAverage(int[] scores) {
      int total = 0;
      double max = 0;
      for (int i = 0; i < scores.length; i++)
         total += scores[i];
      max = total;
      max /= scores.length;
      Arrays.sort(scores);
      for (int k = 1; k * 2 < scores.length; k++) {
         total -= scores[k - 1];
         total -= scores[scores.length - k];
         max = Math.max(max, total / (double) (scores.length - k * 2));
      }
      return max;
   }
}
