package srm149;

public class BigBurger {
   public int maxWait(int[] arrival, int[] service) {
      int n = arrival.length;
      int time = arrival[0];
      int max = 0;
      for (int i = 0; i < n; i++) {
         if (time <= arrival[i])
            time = arrival[i] + service[i];
         else {
            if (time - arrival[i] > max)
               max = time - arrival[i];
            time += service[i];
         }
      }
      return max;
   }
}
