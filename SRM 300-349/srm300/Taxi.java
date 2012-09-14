package srm300;

public class Taxi {
   public double maxDis(int maxX, int maxY, int taxiDis) {
      if (maxX + maxY < taxiDis)
         return -1;
      if (maxX >= taxiDis || maxY >= taxiDis)
         return taxiDis;
      long l = maxX > maxY ? maxX : maxY;
      long s = taxiDis - l;
      return Math.sqrt(l * l + s * s);
   }
}
