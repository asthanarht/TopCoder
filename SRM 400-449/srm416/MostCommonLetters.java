package srm416;

public class MostCommonLetters {
   public String listMostCommon(String[] text) {
      int count[] = new int[26], index = 0;
      char c = 'a';
      for (int i = 0; i < text.length; i++)
         for (int j = 0; j < text[i].length(); j++) {
            c = text[i].charAt(j);
            index = c - 'a';
            if (index >= 0 && index < 26)
               count[index]++;
         }
      int max = 0;
      String result = "";
      for (int i = 0; i < 26; i++) {
         if (count[i] > max) {
            max = count[i];
            result = "" + (char) ('a' + i);
         }
         else if (count[i] == max) {
            result += (char) ('a' + i);
         }
      }
      return result;
   }
}
