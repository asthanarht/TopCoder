package srm313;

import java.util.HashSet;

public class PrefixFreeSets {
   public int maxElements(String[] words) {
      HashSet<String> set = new HashSet<String>();
      for (int i = 0; i < words.length; i++)
         set.add(words[i]);
      int n = set.size();
      for (String pre : set)
         for (String word : set)
            if (word.startsWith(pre) && word.length() != pre.length()) {
               n--;
               break;
            }
      return n;
   }
}
