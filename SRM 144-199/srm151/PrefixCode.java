package srm151;

public class PrefixCode {
   public String isOne(String[] words) {
      int n = words.length;
      for (int i = 0; i < n; i++)
         for (int j = 0; j < n; j++)
            if (i != j) {
               if (words[i].length() <= words[j].length()
                     && words[j].startsWith(words[i]))
                  return "No, " + i;
            }
      return "Yes";
   }
}
