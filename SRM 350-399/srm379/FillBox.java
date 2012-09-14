package srm379;

public class FillBox {
   public int minCubes(int length, int width, int height, int[] cubes) {
      int n = cubes.length;
      int res = 0;
      long filled = 0;
      for (int i = n - 1; i >= 0; i--) {
         int size = (int) Math.pow(2, i);
         int max = (length / size) * (width / size) * (height / size);
         max -= filled / (long) Math.pow(size, 3);
         max = Math.min(max, cubes[i]);
         res += max;
         filled += max * (long) Math.pow(size, 3);
      }
      if (filled == (long) length * width * height)
         return res;
      return -1;
   }
}
