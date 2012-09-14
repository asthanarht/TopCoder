package srm384;

import java.util.HashSet;

public class Library {
   public int documentAccess(String[] records, String[] userGroups,
         String[] roomRights) {
      HashSet<String> g = new HashSet<String>();
      for (int i = 0; i < userGroups.length; i++)
         g.add(userGroups[i]);
      HashSet<String> r = new HashSet<String>();
      for (int i = 0; i < roomRights.length; i++)
         r.add(roomRights[i]);
      HashSet<String> d = new HashSet<String>();
      for (int i = 0; i < records.length; i++) {
         String[] info = records[i].split(" ");
         if (g.contains(info[2]) && r.contains(info[1]))
            d.add(info[0]);
      }
      return d.size();
   }
}
