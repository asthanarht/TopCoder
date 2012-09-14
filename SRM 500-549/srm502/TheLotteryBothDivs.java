package srm502;

import java.util.HashSet;

public class TheLotteryBothDivs {
   public double find(String[] goodSuffixes) {
      double num = 0;
      HashSet<String> sss = new HashSet<String>();
      for (int i = 0; i < goodSuffixes.length; i++)
         sss.add(goodSuffixes[i]);
      String ss[] = sss.toArray(new String[sss.size()]);
      int n = ss.length;
      for (int i = 0; i < n; i++) {
         int l = ss[i].length();
         boolean found = false;
         for (int j = 0; j < n; j++)
            if (j != i) {
               if (l > ss[j].length())
                  if (ss[i].endsWith(ss[j]))
                     found = true;
            }
         if (!found) {
            num += 1 / Math.pow(10, l);
         }
      }
      return 0.000000001 * (int) (num * 1000000000);
   }
}