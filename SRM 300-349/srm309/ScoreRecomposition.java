package srm309;

import java.math.BigInteger;

public class ScoreRecomposition {
   public int minError(String questions, int score) {
      int n = questions.length();
      boolean[] judge = new boolean[n];
      for (int i = 0; i < questions.length(); i++)
         judge[i] = questions.charAt(i) == 'C' ? true : false;
      int c = 0; // correct
      for (int i = 0; i < n; i++)
         if (judge[i])
            c++;
      if (c == 0 && score == 0)
         return 0;
      int error = Integer.MAX_VALUE;
      BigInteger scores = BigInteger.ZERO;
      // the ith bit of scores indicates the correctness of the (i+1) point
      // value question
      for (int i = 1; i < Math.pow(2, n); i++) {
         scores = scores.add(BigInteger.ONE);
         if (scores.bitCount() != c)
            continue;
         int total = c;
         for (int j = 0; j < n; j++)
            if (scores.testBit(j))
               total += j;
         if (total != score)
            continue;
         int e = 0;
         int cIndex = 0;
         int wIndex = 0;
         for (int j = 0; j < n; j++) {
            if (scores.testBit(j)) {
               while (!judge[cIndex])
                  cIndex++;
               e = Math.max(e, Math.abs(j - cIndex));
               cIndex++;
            }
            else {
               while (judge[wIndex])
                  wIndex++;
               e = Math.max(e, Math.abs(j - wIndex));
               wIndex++;
            }
         }
         error = Math.min(error, e);
      }
      if (error == Integer.MAX_VALUE)
         return -1;
      return error;
   }
}
