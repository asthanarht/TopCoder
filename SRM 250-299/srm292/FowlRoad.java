package srm292;

public class FowlRoad {
   public int crossings(int roadY, int[] bobX, int[] bobY) {
      int count = 0;
      int loc = 0;
      for (int i = 0; i < bobY.length; i++)
         if (bobY[i] > roadY) {
            count += loc < 0 ? 1 : 0;
            loc = 1;
         }
         else if (bobY[i] < roadY) {
            count += loc > 0 ? 1 : 0;
            loc = -1;
         }
      return count;
   }
}
