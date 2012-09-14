package srm334;

public class EncodedSum {
   public long maximumSum(String[] numbers) {
      long[] weight = new long[10];
      boolean[] leading = new boolean[10];
      for (int i = 0; i < numbers.length; i++) {
         int len = numbers[i].length();
         leading[numbers[i].charAt(0) - 'A'] = true;
         for (int j = 0; j < len; j++) {
            int index = numbers[i].charAt(j) - 'A';
            weight[index] += Math.pow(10, len - j - 1);
         }
      }
      long min = Long.MAX_VALUE;
      int minI = -1;
      for (int i = 0; i < 10; i++)
         if (!leading[i] && weight[i] < min) {
            min = weight[i];
            minI = i;
         }
      if (minI != -1)
         weight[minI] = 0;
      long res = 0;
      for (int i = 9; i > 0; i--) {
         long max = 0;
         int maxI = -1;
         for (int j = 0; j < 10; j++)
            if (weight[j] > max) {
               max = weight[j];
               maxI = j;
            }
         if (maxI != -1) {
            res += max * i;
            weight[maxI] = 0;
         }
      }
      return res;
   }
}
