package srm375;

import java.util.ArrayList;

public class DivisibleByDigits {
   private long lcm = 1;
   private ArrayList<Long> nums = new ArrayList<Long>();

   public long getContinuation(int n) {
      long num = n;
      while (n > 0) {
         int digit = n % 10;
         n /= 10;
         if (digit == 0)
            continue;
         lcm = lcm(lcm, digit);
      }

      nums.add(num);
      while (nums.size() > 0) {
         num = nums.remove(0);
         if (num % lcm == 0)
            return num;
         num *= 10;
         for (int i = 0; i < 10; i++)
            nums.add(num + i);
      }
      return -1;
   }

   public long gcd(long a, long b) {
      long big, small;
      while (b > 1) {
         if (a >= b) {
            big = a;
            small = b;
         }
         else {
            small = a;
            big = b;
         }
         a = small;
         b = big % small;
      }
      return b == 0 ? a : 1;
   }

   public long lcm(long a, long b) {
      return a * b / gcd(a, b);
   }
}
