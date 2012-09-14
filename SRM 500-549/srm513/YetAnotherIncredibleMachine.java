package srm513;

import java.util.Arrays;

public class YetAnotherIncredibleMachine {
   public int countWays(int[] platformMount, int[] platformLength, int[] balls) {
      Arrays.sort(balls);
      int n = platformMount.length;
      int m = balls.length;
      long result = 1;
      int mod = 1000000009;
      for (int i = 0; i < n; i++) {
         int count = 0;
         int left = Integer.MIN_VALUE;
         int right = Integer.MAX_VALUE;
         int loc = platformMount[i];
         boolean ok = true;
         for (int j = 0; j < m; j++) {
            if (balls[j] < loc)
               left = balls[j];
            else if (balls[j] == loc) {
               ok = false;
               break;
            }
            else {
               right = balls[j];
               break;
            }
         }
         if (!ok)
            return 0;
         int length = platformLength[i];
         if (left < loc - length)
            left = loc - length - 1;
         if (right > loc + length)
            right = loc + length + 1;
         count = right - left - 1 - length;
         if (count <= 0)
            return 0;
         result *= count;
         result %= mod;
      }
      return (int) result;
   }
}
