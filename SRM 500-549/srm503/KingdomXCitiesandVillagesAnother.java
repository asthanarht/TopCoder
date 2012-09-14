package srm503;

import java.util.HashSet;

public class KingdomXCitiesandVillagesAnother {

   public double determineLength(int[] cityX, int[] cityY, int[] villageX,
         int[] villageY) {
      double min = 0;

      HashSet<Integer> connectedVillages = new HashSet<Integer>();

      int cityMax = cityX.length;
      int villageMax = villageX.length;

      // get the first

      double shortest = distance(cityX[0], cityY[0], villageX[0], villageY[0]);
      int shortestIndex = 0;
      double dis;
      // for each city
      for (int i = 0; i < cityMax; i++)
         // for each village
         for (int j = 0; j < villageMax; j++) {
            // find the shortest to connect
            dis = distance(cityX[i], cityY[i], villageX[j], villageY[j]);
            if (dis < shortest) {
               shortest = dis;
               shortestIndex = j;
            }
         }
      connectedVillages.add(shortestIndex);
      min += shortest;

      // for the rest

      // while visited.size < village.length
      while (connectedVillages.size() < villageMax) {

         // find any unvisited to be the first shortest
         for (int j = 0; j < villageMax; j++)
            if (!connectedVillages.contains(j)) {
               shortest = distance(cityX[0], cityY[0], villageX[j], villageY[j]);
               shortestIndex = j;
            }

         // for each unvisited
         for (int j = 0; j < villageMax; j++)
            if (!connectedVillages.contains(j))
               // for each city
               for (int i = 0; i < cityMax; i++) {
                  // find the shortest to connect
                  dis = distance(cityX[i], cityY[i], villageX[j], villageY[j]);
                  if (dis < shortest) {
                     shortest = dis;
                     shortestIndex = j;
                  }
               }

         // for each unvisited
         for (int j = 0; j < villageMax; j++)
            if (!connectedVillages.contains(j))
               // for each visited village
               for (int i = 0; i < villageMax; i++)
                  if (connectedVillages.contains(i)) {
                     // find the shortest to connect
                     dis = distance(villageX[i], villageY[i], villageX[j],
                           villageY[j]);
                     if (dis < shortest) {
                        shortest = dis;
                        shortestIndex = j;
                     }
                  }

         // connect
         connectedVillages.add(shortestIndex);
         min += shortest;

      }

      return min;
   }

   private double distance(int x1, int y1, int x2, int y2) {
      long x = x1 - x2;
      long y = y1 - y2;
      double num = Math.sqrt(x * x + y * y);
      return num;
   }

}
