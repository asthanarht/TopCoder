package srm209;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class MedalTable {
   public String[] generate(String[] results) {
      HashMap<String, Country> map = new HashMap<String, Country>();
      ArrayList<Country> list = new ArrayList<Country>();
      for (int i = 0; i < results.length; i++) {
         String[] names = results[i].split(" ");
         for (int j = 0; j < 3; j++) {
            Country country = map.get(names[j]);
            if (country != null)
               country.medle[j]++;
            else {
               country = new Country();
               country.name = names[j];
               country.medle[j]++;
               map.put(names[j], country);
               list.add(country);
            }
         }
      }
      int n = list.size();
      Country[] countrys = new Country[n];
      for (int i = 0; i < list.size(); i++)
         countrys[i] = list.get(i);
      Arrays.sort(countrys, new CountryComparator());
      String[] scoreBoard = new String[n];
      for (int i = 0; i < n; i++)
         scoreBoard[i] = countrys[i].toString();
      return scoreBoard;
   }
}

class Country {
   public String name = "";
   public int[] medle = { 0, 0, 0 };

   public String toString() {
      String score = name;
      for (int i = 0; i < 3; i++)
         score += " " + medle[i];
      return score;
   }
}

class CountryComparator implements Comparator<Country> {
   public int compare(Country o1, Country o2) {
      if (!(o1 instanceof Country))
         throw new ClassCastException("Invalid object");
      if (!(o2 instanceof Country))
         throw new ClassCastException("Invalid object");
      Country one = (Country) o1;
      Country two = (Country) o2;
      for (int i = 0; i < 3; i++)
         if (two.medle[i] > one.medle[i])
            return 1;
         else if (two.medle[i] < one.medle[i])
            return -1;
         else
            continue;
      return one.name.compareTo(two.name);
   }
}
