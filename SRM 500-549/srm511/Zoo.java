package srm511;

import java.util.Arrays;

public class Zoo {
   public long theCount(int[] answers) {
      Arrays.sort(answers);
      int n = answers.length;

      int count = 0;
      for (int i = 0; i < n; i++)
         if (answers[i] == i / 2)
            count++;
         else
            break;
      if (count % 2 == 1)
         count--;
      count--;

      int start = 0;
      if (count != -1)
         start = answers[count] + 1;
      for (int i = count + 1; i < n; i++)
         if (answers[i] != start)
            return 0;
         else
            start++;

      int pow = (count + 1) / 2;
      long result = (long) Math.pow(2, pow);
      if (count == n - 1)
         return result;
      else
         return result * 2;
   }
}
