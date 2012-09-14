package srm369;

public class BeautifulString {
   public int maximumLength(int countA, int countB, int maxA, int maxB) {
      int segA = maxA == 0 ? 0 : (countA + maxA - 1) / maxA;
      int segB = maxB == 0 ? 0 : (countB + maxB - 1) / maxB;
      int result = 0;
      if (Math.min(segA, segB) == 0) {
         if (segB != 0)
            result = segB > 1 ? maxB : countB;
         else if (segA != 0)
            result = segA > 1 ? maxA : countA;
      }
      else {
         if (segB > segA && countA < countB / maxB)
            result = countA * (1 + maxB) + maxB;
         else if (segA > segB && countB < countA / maxA)
            result = countB * (1 + maxA) + maxA;
         else
            result = countA + countB;
      }
      return result;
   }
}
