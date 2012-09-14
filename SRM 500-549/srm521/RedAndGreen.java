package srm521;

public class RedAndGreen {
   public int minPaints(String row) {
      int count = 0;
      int min = Integer.MAX_VALUE;
      for (int i = 0; i <= row.length(); i++) {
         count = 0;
         for (int j = 0; j < i; j++)
            if (row.charAt(j) != 'R')
               count++;
         for (int j = i; j < row.length(); j++)
            if (row.charAt(j) != 'G')
               count++;
         if (count < min)
            min = count;
      }
      return min;
   }
}
