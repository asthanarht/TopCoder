package srm370;

import java.math.BigInteger;

public class DrawingMarbles {
   public double sameColor(int[] colors, int n) {
      BigInteger total = BigInteger.ZERO;
      int count = 0;
      for (int i = 0; i < colors.length; i++) {
         count += colors[i];
         if (colors[i] >= n)
            total = total.add(c(colors[i], n));
      }
      return total.doubleValue() / c(count, n).doubleValue();
   }

   private BigInteger c(int n, int k) {
      BigInteger total = BigInteger.ONE;
      BigInteger num = new BigInteger("" + n);
      for (int i = 0; i < k; i++) {
         total = total.multiply(num);
         num = num.subtract(BigInteger.ONE);
      }
      return total;
   }
}
