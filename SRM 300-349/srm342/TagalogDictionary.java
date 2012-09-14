package srm342;

import java.util.Arrays;
import java.util.HashMap;

public class TagalogDictionary {
   public static HashMap<Character, Integer> order = new HashMap<Character, Integer>();

   public String[] sortWords(String[] words) {
      init();
      Word[] list = new Word[words.length];
      for (int i = 0; i < words.length; i++)
         list[i] = new Word(words[i]);
      Arrays.sort(list);
      String[] res = new String[words.length];
      for (int i = 0; i < words.length; i++)
         res[i] = list[i].word;
      return res;
   }

   private void init() {
      order.put('a', 1);
      order.put('b', 2);
      order.put('k', 3);
      order.put('d', 4);
      order.put('e', 5);
      order.put('g', 6);
      order.put('h', 7);
      order.put('i', 8);
      order.put('l', 9);
      order.put('m', 10);
      order.put('n', 11);
      order.put('o', 13);
      order.put('p', 14);
      order.put('r', 15);
      order.put('s', 16);
      order.put('t', 17);
      order.put('u', 18);
      order.put('w', 19);
      order.put('y', 20);
   }
}

class Word implements Comparable<Object> {

   String word;

   public Word(String word) {
      super();
      this.word = word;
   }

   public int compareTo(Object o) {
      String w = ((Word) o).word;
      for (int i = 0, j = 0;; i++, j++) {
         if (i >= word.length())
            return j < w.length() ? -1 : 0;
         else if (j >= w.length())
            return 1;
         char s1 = word.charAt(i);
         char s2 = w.charAt(j);
         int index1 = TagalogDictionary.order.get(s1);
         int index2 = TagalogDictionary.order.get(s2);
         if (index1 == 11 && i + 1 < word.length() && word.charAt(i + 1) == 'g') {
            index1 = 12;
            i++;
         }
         if (index2 == 11 && j + 1 < w.length() && w.charAt(j + 1) == 'g') {
            index2 = 12;
            j++;
         }
         if (index1 != index2)
            return index1 - index2;
      }
   }
}