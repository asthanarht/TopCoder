package srm335;

public class Multifactorial {
   public String calcMultiFact(int n, int k) {
      long res = 1, N = n, K = k;
      for (long x = 0; x < (N + K - 1) / K; x++) {
         long f = N - x * K;
         if (res <= 1000000000000000000L / f)
            res *= f;
         else
            return "overflow";
      }
      return String.valueOf(res);
   }
}
