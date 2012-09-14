package srm353;

import java.util.ArrayList;
import java.util.Arrays;

public class Glossary {
   public String[] buildGlossary(String[] items) {
      Group[] group = new Group[26];
      for (int i = 0; i < 26; i++)
         group[i] = new Group((char) ('A' + i));
      for (int i = 0; i < items.length; i++) {
         int index = Character.toLowerCase(items[i].charAt(0)) - 'a';
         group[index].add(new Item(items[i]));
      }
      for (int i = 0; i < 26; i++)
         group[i].sort();

      int count1 = 0, count2 = 0;
      for (int i = 0; i < 'n' - 'a'; i++)
         count1 += group[i].linecount();
      String[] left = new String[count1];
      for (int i = 0, j = 0; i < 'n' - 'a'; i++)
         for (int k = 0; k < group[i].linecount(); k++)
            left[j++] = group[i].getline(k);

      for (int i = 'n' - 'a'; i < 26; i++)
         count2 += group[i].linecount();
      String[] right = new String[count2];
      for (int i = 'n' - 'a', j = 0; i < 26; i++)
         for (int k = 0; k < group[i].linecount(); k++)
            right[j++] = group[i].getline(k);

      String[] res = new String[Math.max(count1, count2)];
      for (int i = 0; i < res.length; i++) {
         if (i < count1)
            res[i] = left[i];
         else
            res[i] = "                   ";
         if (i < count2)
            res[i] += "  " + right[i];
         else
            res[i] += "                     ";
      }
      return res;
   }
}

class Group {
   char name;

   public Group(char name) {
      super();
      this.name = name;
   }

   ArrayList<Item> items = new ArrayList<Item>();
   Item[] list;

   public void add(Item item) {
      items.add(item);
   }

   public void sort() {
      list = new Item[items.size()];
      for (int i = 0; i < items.size(); i++)
         list[i] = items.get(i);
      Arrays.sort(list);
   }

   public int linecount() {
      if (items.size() == 0)
         return 0;
      return 2 + items.size();
   }

   public String getline(int i) {
      if (i == 0)
         return String.format("%-19c", name);
      else if (i == 1)
         return "-------------------";
      else
         return String.format("  %-17s", list[i - 2].name);
   }
}

class Item implements Comparable<Object> {
   String name;
   String lc;

   public Item(String name) {
      super();
      this.name = name;
      lc = name.toLowerCase();
   }

   public int compareTo(Object arg0) {
      return lc.compareTo(((Item) arg0).lc);
   }

   public String toString() {
      return String.format("  %-17s", name);
   }
}