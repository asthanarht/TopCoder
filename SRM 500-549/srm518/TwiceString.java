package srm518;

public class TwiceString {
   public static String getShortest(String s) {
      for (int i = 1; i < s.length(); i++) {
         boolean ok = true;
         for (int j = 0; i + j < s.length(); j++)
            if (s.charAt(j) != s.charAt(i + j)) {
               ok = false;
               break;
            }
         if (ok)
            return s.substring(0, i) + s;
      }
      return s + s;
   }
}
