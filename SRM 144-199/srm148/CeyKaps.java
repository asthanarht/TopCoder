package srm148;

public class CeyKaps {
   public String decipher(String typed, String[] switches) {
      int m = typed.length();
      int n = switches.length;
      char[] s = typed.toCharArray();

      for (int i = 0; i < n; i++) {
         char c1 = switches[i].charAt(0);
         char c2 = switches[i].charAt(2);
         for (int j = 0; j < m; j++)
            if (s[j] == c1)
               s[j] = c2;
            else if (s[j] == c2)
               s[j] = c1;
      }

      String result = "";
      for (int j = 0; j < m; j++)
         result += s[j];
      return result;
   }
}
