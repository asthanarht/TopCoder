package srm381;

import java.util.ArrayList;
import java.util.Arrays;

public class TheHomework {
   public int transform(int[] first, int[] second) {
      Arrays.sort(first);
      Arrays.sort(second);
      int count = 0;
      for (int i = 0, j = 0; i < first.length; i++) {
         while (j < second.length && second[j] < first[i])
            j++;
         if (j == second.length)
            break;
         if (first[i] == second[j]) {
            count++;
            j++;
         }
      }

      ArrayList<Integer> size = new ArrayList<Integer>();
      ArrayList<Integer> common = new ArrayList<Integer>();

      size.add(first.length);
      common.add(count);
      int move = 0;
      do {
         ArrayList<Integer> nsize = new ArrayList<Integer>();
         ArrayList<Integer> ncommon = new ArrayList<Integer>();
         for (int i = 0; i < size.size(); i++) {
            int s = size.get(i);
            int c = common.get(i);
            if (s == second.length && c == second.length)
               return move;
            int change = s / 2;
            if (c < second.length) {
               int add = Math.min(second.length - c, s);
               nsize.add(s + add);
               ncommon.add(c + add);
               int add2 = Math.min(second.length - s, s);
               if (add2 != add) {
                  nsize.add(s + add2);
                  ncommon.add(c + add2);
               }
            }
            if (s > second.length) {
               int minus = Math.min(s - second.length, change);
               nsize.add(s - minus);
               ncommon.add(c);
               if (c < second.length && minus < change) {
                  minus = Math.min(s - c, change);
                  nsize.add(s - minus);
                  ncommon.add(c);
               }
            }
            if (s > c && c < second.length) {
               int swap = Math.min(s - c, change);
               swap = Math.min(swap, second.length - c);
               nsize.add(s);
               ncommon.add(c + swap);
            }
         }
         size = nsize;
         common = ncommon;
         move++;
      } while (size.size() > 0);

      return -1;
   }
}
