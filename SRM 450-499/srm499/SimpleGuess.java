package srm499;

public class SimpleGuess {
   public int getMaximum(int[] hints) {
      int max = 0;
      for (int i = 0; i < hints.length; i++)
         for (int j = 0; j < hints.length; j++)
            if (i != j) {
               int sum = hints[i];
               int dif = hints[j];
               int x = sum + dif;
               if (x % 2 != 0)
                  continue;
               x /= 2;
               int y = sum - x;
               int mul = x * y;
               if (mul > max)
                  max = mul;
            }
      return max;
   }
}
