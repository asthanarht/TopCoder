package srm347;

public class Aircraft {
   public String nearMiss(int[] p1, int[] v1, int[] p2, int[] v2, int R) {
      long[] p = new long[3], v = new long[3];
      for (int i = 0; i < 3; i++) {
         p[i] = p1[i] - p2[i];
         v[i] = v1[i] - v2[i];
      }
      long A = v[0] * v[0] + v[1] * v[1] + v[2] * v[2];
      long B = (p[0] * v[0] + p[1] * v[1] + p[2] * v[2]) * 2;
      long C = p[0] * p[0] + p[1] * p[1] + p[2] * p[2] - R * R;
      if (C <= 0) // currently less than R
         return "YES";
      long N = B * B - 4 * A * C;
      if (A == 0) {
         if (B < 0)
            return "YES";
         else
            return "NO";
      }
      if (N >= 0 && (B < 0 || (B > 0 && N > B * B))) // non-negative root
         return "YES";
      return "NO";
   }
   /*
    * editorial solution
    * http://community.topcoder.com/tc?module=Static&d1=match_editorials&d2=srm347
    * 
    * if (A == 0) return "NO";
    * since A==0 ==> B==0
    * 
    * if (B >= 0) return "NO";
    * since -b + sqrt(b*b - 4*a*c) < -b + sqrt(b*b) = 0
    * only B<0 can have non-negative toor
    */
}

