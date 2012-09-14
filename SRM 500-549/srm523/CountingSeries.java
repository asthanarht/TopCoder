package srm523;

public class CountingSeries {
   public long countThem(long a, long b, long c, long d, long upperBound) {
      long count = 0;
      if (a <= upperBound) {
         count = (upperBound - a) / b;
         count++;
      }
      if (d == 1) {
         if (c <= upperBound && (c < a || (c - a) % b != 0))
            count++;
      }
      else {
         for (long i = c; i <= upperBound; i *= d)
            if (i < a)
               count++;
            else if ((i - a) % b != 0)
               count++;
      }
      return count;
   }
}
