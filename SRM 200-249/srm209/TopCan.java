package srm209;

public class TopCan {
   public double minSurface(int volume) {
      double r = Math.cbrt(volume / (2.0 * Math.PI));
      double h = volume / (Math.PI * r * r);
      return 2.0 * Math.PI * r * (r + h);
   }
}
