package srm391;

import java.util.HashMap;

public class IsomorphicWords {
   public int countPairs(String[] words) {
      int count = 0;
      for (int i = 0; i < words.length; i++)
         for (int j = i + 1; j < words.length; j++)
            if (isIsomorphic(words[i], words[j]))
               count++;
      return count;
   }

   private boolean isIsomorphic(String word1, String word2) {
      HashMap<Character, Character> mapping = new HashMap<Character, Character>();
      for (int i = 0; i < word1.length(); i++) {
         char c1 = word1.charAt(i);
         char c2 = word2.charAt(i);
         if (mapping.get(c1) == null) {
            if (word2.indexOf(c2) == i)
               mapping.put(c1, c2);
            else
               return false;
         }
         else if (mapping.get(c1) != c2)
            return false;
      }
      return true;
   }
}
