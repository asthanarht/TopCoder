package srm334;

import java.util.ArrayList;

public class ExtendedHappyNumbers {
   private int k;
   private int[] pow = new int[10];

   public long calcTheSum(int A, int B, int K) {
      k = K;
      for (int i = 0; i < 10; i++)
         pow[i] = (int) Math.pow(i, k);
      boolean[] tick = new boolean[10000000]; // counted
      int[] happy = new int[10000000]; // happiness
      for (int i = A; i <= B; i++)
         if (!tick[i]) {
            ArrayList<Integer> sequence = new ArrayList<Integer>();
            int n = i;
            boolean period = false;
            while (!tick[n]) {
               if (sequence.contains(n)) {
                  period = true;
                  break;
               }
               sequence.add(n);
               n = skn(n);
            }
            if (!period) {
               int min = happy[n];
               for (int j = sequence.size() - 1; j >= 0; j--) {
                  min = Math.min(min, sequence.get(j));
                  tick[sequence.get(j)] = true;
                  happy[sequence.get(j)] = min;
               }
            }
            else {
               int start = 0;
               for (; start < sequence.size(); start++)
                  if (sequence.get(start) == n)
                     break;
               int min = n;
               for (int j = start; j < sequence.size(); j++)
                  min = Math.min(min, sequence.get(j));
               for (int j = start; j < sequence.size(); j++) {
                  tick[sequence.get(j)] = true;
                  happy[sequence.get(j)] = min;
               }
               for (int j = start - 1; j >= 0; j--) {
                  min = Math.min(min, sequence.get(j));
                  tick[sequence.get(j)] = true;
                  happy[sequence.get(j)] = min;
               }
            }
         }
      long res = 0;
      for (int i = A; i <= B; i++)
         res += happy[i];
      return res;
   }

   private int skn(int n) {
      int skn = 0;
      while (n > 0) {
         skn += pow[n % 10];
         n /= 10;
      }
      return skn;
   }
}
