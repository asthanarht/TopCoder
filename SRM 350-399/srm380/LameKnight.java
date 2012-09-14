package srm380;

public class LameKnight {
   public int maxCells(int height, int width) {
      int max = 1;
      if (height == 2)
         max += (Math.min(width, 7) - 1) / 2;
      if (height > 2) {
         if (width < 5)
            max = width;
         if (width == 5 || width == 6)
            max = 4;
         if (width > 6)
            max = width - 2;
      }
      return max;
   }
}
