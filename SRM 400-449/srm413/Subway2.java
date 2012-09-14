package srm413;

public class Subway2 {
   public double minTime(int length, int maxAcceleration, int maxVelocity) {
      double l = (double) length;
      double a = (double) maxAcceleration;
      double v = (double) maxVelocity;

      double t = v / a;
      double s = 0.5 * a * t * t;
      if (2 * s >= l)
         return 2 * Math.sqrt(l / a);
      else
         return t * 2 + (l - 2 * s) / v;
   }
}
