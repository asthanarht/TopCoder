package srm235;

import java.util.Arrays;

public class NoisySensor {
   public int[] medianFilter(int[] data) {
      int[] median = data.clone();
      for (int i = 1; i < data.length - 1; i++)
         median[i] = getMedian(data[i - 1], data[i], data[i + 1]);
      return median;
   }

   private int getMedian(int a, int b, int c) {
      int[] array = { a, b, c };
      Arrays.sort(array);
      return array[1];
   }
}
