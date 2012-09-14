package srm519;

public class BinaryCards {
   public long largestNumber(long A, long B) {
      if (A == B)
         return A;
      boolean a[] = new boolean[64];
      boolean b[] = new boolean[64];
      long num = 1;
      long aa = A;
      long bb = B;
      for (int i = 1; i < 64; i++) {
         a[i] = aa % 2 == 1 ? true : false;
         b[i] = bb % 2 == 1 ? true : false;
         aa /= 2;
         bb /= 2;
      }
      int Max = 0;
      for (int i = 63; i > 0; i--)
         if (a[i] != b[i]) {
            Max = i;
            break;
         }
      long result = 1;
      num = 1;
      for (int i = 1; i < Max; i++) {
         num *= 2;
         result += num;
      }
      for (int i = Max + 1; i < 64; i++) {
         num *= 2;
         if (b[i])
            result += num;
      }
      return result;
   }
}
