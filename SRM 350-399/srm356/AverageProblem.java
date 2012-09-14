package srm356;

import java.util.HashSet;

public class AverageProblem {
   private HashSet<Integer> ns = new HashSet<Integer>();

   public int numberOfParticipants(String[] marks) {
      for (int i = 0; i < marks.length; i++) {
         String[] mark = marks[i].split(" ");
         for (int j = 0; j < mark.length; j++) {
            String m = mark[j].replace(".", "");
            int n = Integer.parseInt(m);
            ns.add(n);
         }
      }
      return min();
   }

   private int min() {
      for (int i = 1; i < 1000; i++) {
         boolean ok = true;
         for (Integer n : ns) {
            int min = n * i, max = min + i;
            int a1 = min / 1000 * 1000, a2 = a1 + 1000;
            if (!(min <= a1 && a1 < max) && !(min <= a2 && a2 < max)) {
               ok = false;
               break;
            }
         }
         if (ok)
            return i;
      }
      return 1000;
   }
}
