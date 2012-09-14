package srm539;

import java.util.Arrays;

public class Over9000Rocks {
   public int countPossibilities(int[] lowerBound, int[] upperBound) {
      int n = lowerBound.length;
      Interval[] intervals = new Interval[1 << n];
      for (int i = 0; i < 1 << n; i++) {
         int low = 0, high = 0;
         for (int j = 0; j < n; j++)
            if ((i & (1 << j)) > 0) {
               low += lowerBound[j];
               high += upperBound[j];
            }
         intervals[i] = new Interval(low, high);
      }
      Arrays.sort(intervals);
      int res = 0, upto = 9000;
      for (int i = 0; i < 1 << n; i++)
         if (intervals[i].high > upto) {
            res += intervals[i].high - Math.max(upto, intervals[i].low - 1);
            upto = intervals[i].high;
         }
      return res;
   }
}

class Interval implements Comparable<Object> {
   int low, high;

   public Interval(int low, int high) {
      this.low = low;
      this.high = high;
   }

   public int compareTo(Object o) {
      return low - ((Interval) o).low;
   }
}