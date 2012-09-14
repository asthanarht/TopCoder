package srm399;

public class MaximalProduct {
   public long maximalProduct(int s, int k) {
      int small = s / k;
      int bigCount = s % k;
      long pro = 1;
      for (int i = 0; i < k - bigCount; i++)
         pro *= small;
      for (int i = 0; i < bigCount; i++)
         pro *= (small + 1);
      return pro;
   }
}
