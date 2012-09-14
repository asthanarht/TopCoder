package srm312;

import java.util.Scanner;

public class ParallelepipedUnion {
   private int[][][] loc = new int[2][2][3];

   public int getVolume(String[] parallelepipeds) {
      for (int i = 0; i < 2; i++) {
         Scanner sc = new Scanner(parallelepipeds[i]);
         for (int j = 0; j < 2; j++)
            for (int k = 0; k < 3; k++)
               loc[i][j][k] = sc.nextInt();
      }
      int vol = 0;
      for (double i = 1.5; i <= 99.5; i += 1)
         for (double j = 1.5; j <= 99.5; j += 1)
            for (double k = 1.5; k <= 99.5; k += 1)
               if (isInside(i, j, k))
                  vol++;
      return vol;
   }

   private boolean isInside(double i, double j, double k) {
      for (int index = 0; index < 2; index++) {
         if (i > loc[index][0][0] && i < loc[index][1][0])
            if (j > loc[index][0][1] && j < loc[index][1][1])
               if (k > loc[index][0][2] && k < loc[index][1][2])
                  return true;
      }
      return false;
   }
}
