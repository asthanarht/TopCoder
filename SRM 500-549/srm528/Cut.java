package srm528;
import java.util.Arrays;

public class Cut {
   public int getMaximum(int[] eelLengths, int maxCuts) {
      Arrays.sort(eelLengths);
      int count = 0;
      for (int i = 0; i < eelLengths.length; i++)
         if (eelLengths[i] == 10) {
            count++;
            eelLengths[i] = 0;
         }
         else if (maxCuts > 0 && eelLengths[i] > 10 && eelLengths[i] % 10 == 0) {
            if (maxCuts >= eelLengths[i] / 10 - 1) {
               count += eelLengths[i] / 10;
               maxCuts -= eelLengths[i] / 10 - 1;
               eelLengths[i] = 0;
            }
            else {
               count += maxCuts;
               maxCuts = 0;
               break;
            }
         }
      if (maxCuts > 0)
         for (int i = 0; i < eelLengths.length; i++)
            if (eelLengths[i] > 10) {
               int min = Math.min(maxCuts, eelLengths[i] / 10);
               count += min;
               maxCuts -= min;
            }
      return count;
   }
}
