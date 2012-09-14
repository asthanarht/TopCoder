package srm389;

public class ApproximateDivision {
   public double quotient(int a, int b, int terms) {
      int t = 1;
      for (int i = 1; i < 32 && t < b; i++)
         t = t << 1;
      int c = t - b;
      double result = 0;
      for (int i = 0; i < terms; i++)
         result += Math.pow(c, i) / Math.pow(t, i + 1);
      return result * a;
   }
}
