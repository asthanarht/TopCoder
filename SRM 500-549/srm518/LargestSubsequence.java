package srm518;

public class LargestSubsequence {
   public String getLargest(String s) {
      String result = "";
      int index = 0;
      while (index < s.length()) {
         char max = 'a';
         int maxIndex = index;
         for (int i = index; i < s.length(); i++)
            if (s.charAt(i) > max) {
               max = s.charAt(i);
               maxIndex = i;
            }
         result += max;
         index = maxIndex + 1;
      }
      return result;
   }
}
