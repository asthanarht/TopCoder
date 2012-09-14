package srm535;

import java.math.BigInteger;

public class FoxAndGCDLCM {
   public static long get(long G, long L) {
      if (L % G != 0)
         return -1;
      BigInteger g = new BigInteger("" + G);
      BigInteger l = new BigInteger("" + L);
      BigInteger ab = g.multiply(l);
      long min = -1;
      for (BigInteger a = g; a.multiply(a).compareTo(ab) <= 0; a = a.add(g))
         if (l.mod(a).compareTo(BigInteger.ZERO) == 0) {
            long b = ab.divide(a).longValue();
            if (gcd(a.longValue(), b) == G)
               min = min == -1 ? a.longValue() + b : Math.min(min,
                     a.longValue() + b);
         }
      return min;
   }

   public static long gcd(long a, long b) {
      while (b > 1) {
         long big = a >= b ? a : b;
         long small = a >= b ? b : a;
         a = small;
         b = big % small;
      }
      if (b == 0)
         return a;
      else
         return 1;
   }
}
