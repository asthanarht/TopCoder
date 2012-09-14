package srm365;

import java.util.HashSet;

public class PointsOnCircle {
   public long count(int r) {
      HashSet<Long> divisor = new HashSet<Long>();
      for (long i = 1; i <= Math.sqrt(r); i++)
         if (r % i == 0) {
            divisor.add(i);
            divisor.add(r / i);
         }
      HashSet<Long> all = new HashSet<Long>();
      for (Long divisor1 : divisor) {
         all.add(divisor1);
         for (Long divisor2 : divisor)
            all.add(divisor1 * divisor2);
      }

      long d1 = 0, d3 = 0;
      for (Long div : all)
         if (div % 4 == 1)
            d1++;
         else if (div % 4 == 3)
            d3++;
      return 4 * (d1 - d3);
   }
}