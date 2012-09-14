package srm200;

public class NCMultiplication {
   public long findFactors(int[] digits) {
      long min = Long.MAX_VALUE;
      long A = -1;
      long multi = 0;
      for (int i = 0; i < digits.length; i++) {
         multi *= 10;
         multi += digits[i];
      }
      for (long i = 1; i <= Math.sqrt(multi); i++)
         if (multi % i == 0) {
            long a = Math.max(multi / i, i);
            long b = multi / a;
            if (check(a, b, digits) && Math.abs(a - b) < min) {
               min = Math.abs(a - b);
               A = a;
            }
         }
      return A;
   }

   private boolean check(long a, long b, int[] digits) {
      String astr = String.valueOf(a);
      String bstr = String.valueOf(b);
      char[] ach = astr.toCharArray();
      char[] bch = bstr.toCharArray();
      int[] nc = new int[astr.length() + bstr.length() - 1];
      if (nc.length != digits.length)
         return false;
      for (int i = 0; i < ach.length; i++)
         for (int j = 0; j < bch.length; j++)
            nc[i + j] += Integer.parseInt("" + ach[i])
                  * Integer.parseInt("" + bch[j]);
      for (int i = 0; i < digits.length; i++)
         if (nc[i] != digits[i])
            return false;
      return true;
   }
}
