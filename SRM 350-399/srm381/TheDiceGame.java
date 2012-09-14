package srm381;

import java.util.Arrays;

public class TheDiceGame {
   public static double expectedThrows(int candies) {
      int n = candies;
      double[] cast = new double[n + 10];
      Arrays.fill(cast, 1);
      for (int i = 1; i < n; i++) {
         double d = cast[i] / 6.0;
         for (int j = 1; j <= 6; j++)
            cast[i + j] += d;
      }
      return cast[n];
   }
}
