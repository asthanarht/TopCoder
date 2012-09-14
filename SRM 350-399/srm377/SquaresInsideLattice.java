package srm377;

public class SquaresInsideLattice {
   public long howMany(int width, int height) {
      long res = 0;
      for (int i = 1; i <= width && i <= height; i++)
         res += (width - i + 1) * (long) (height - i + 1) * i;
      return res;
   }
}
