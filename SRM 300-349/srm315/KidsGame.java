package srm315;

public class KidsGame {
   public int kthKid(int n, int m, int k) {
      int res = 1;
      k--;
      m--;
      int cur = (m % n + n) % n;
      while (true) {
         if (cur == k)
            break;
         if (cur < k)
            k--;
         n--;
         res++;
         cur = ((cur + m) % n + n) % n;
      }
      return res;
   }
}
