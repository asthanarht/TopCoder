package srm310;

import java.util.Arrays;

public class MeasuringTemperature {
   public double averageTemperature(int[] measuredValues) {
      boolean[] valid = new boolean[measuredValues.length];
      Arrays.fill(valid, false);
      for (int i = 0; i < measuredValues.length; i++) {
         if (measuredValues[i] < -273)
            continue;
         int count = 0;
         int expected = 0;
         for (int j = -2; j <= 2; j++)
            if (j != 0 && i + j >= 0 && i + j < measuredValues.length) {
               expected++;
               if (Math.abs(measuredValues[i + j] - measuredValues[i]) > 2)
                  count++;
            }
         if (count == expected)
            continue;
         valid[i] = true;
      }
      int count = 0;
      double ave = 0;
      for (int i = 0; i < measuredValues.length; i++)
         if (valid[i]) {
            count++;
            ave += measuredValues[i];
         }
      if (count == 0)
         return -300;
      ave /= count;
      return ave;
   }
}
