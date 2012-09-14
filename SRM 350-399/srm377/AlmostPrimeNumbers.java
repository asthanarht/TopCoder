package srm377;

import java.util.Arrays;

public class AlmostPrimeNumbers {
   public int getNext(int m) {
      boolean[] primes = prime(m * 2 + 2000);
      for (int i = 2; i < 11; i++) {
         int num = i;
         while (num < primes.length) {
            primes[num] = true;
            num += i;
         }
      }
      int min = 121;
      for (int i = m + 1; i < primes.length; i++)
         if (!primes[i]) {
            min = i;
            break;
         }
      return min;
   }

   public static boolean[] prime(int n) {
      boolean[] primes = new boolean[n + 1];
      Arrays.fill(primes, true);
      for (int i = 2; i <= n; i++)
         if (primes[i])
            for (int multi = i + i; multi < primes.length; multi += i)
               primes[multi] = false;
      return primes;
   }
}