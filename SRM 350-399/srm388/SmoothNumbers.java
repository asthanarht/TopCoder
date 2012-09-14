package srm388;

import java.util.Arrays;

public class SmoothNumbers {
   public int countSmoothNumbers(int N, int k) {
      boolean[] primes = new boolean[N + 1];
      boolean[] tick = new boolean[N + 1];
      Arrays.fill(primes, true);
      Arrays.fill(tick, true);
      for (int i = 2; i <= N; i++)
         if (primes[i])
            for (int multi = i + i; multi < primes.length; multi += i)
               primes[multi] = false;
      for (int i = k + 1; i < tick.length; i++)
         if (primes[i])
            for (int multi = i; multi <= N; multi += i)
               tick[multi] = false;
      int count = 0;
      for (int i = 1; i <= N; i++)
         if (tick[i])
            count++;
      return count;
   }
}
