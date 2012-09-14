package srm515;

import java.util.HashSet;

public class FortunateNumbers {
   public int getFortunate(int[] a, int[] b, int[] c) {
      HashSet<Integer> set = new HashSet<Integer>();
      for (int i = 0; i < a.length; i++)
         for (int j = 0; j < b.length; j++)
            for (int k = 0; k < c.length; k++) {
               int sum = a[i] + b[j] + c[k];
               if (isFortunate(sum))
                  set.add(sum);
            }
      return set.size();
   }

   public boolean isFortunate(int sum) {
      String s = "" + sum;
      for (int i = 0; i < s.length(); i++)
         if (s.charAt(i) != '5' && s.charAt(i) != '8')
            return false;
      return true;
   }
}
