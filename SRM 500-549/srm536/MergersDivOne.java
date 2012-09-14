package srm536;

import java.util.Arrays;

public class MergersDivOne {
   public double findMaximum(int[] revenues) {
      double res = revenues[0];
      Arrays.sort(revenues);
      for (int i = 1; i < revenues.length; i++) {
         res += revenues[i];
         res /= 2;
      }
      return res;
   }
}
