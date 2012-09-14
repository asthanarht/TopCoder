package srm151;

public class Birthday {
   public String getNext(String date, String[] birthdays) {
      int n = birthdays.length;

      int minm = 12;
      int mind = 31;
      int minindex = 0;

      boolean found = false;

      int datem = Integer.parseInt(date.substring(0, date.indexOf("/")));
      int dated = Integer.parseInt(date.substring(date.indexOf("/") + 1));

      int firstm = 12;
      int firstd = 31;
      int firstindex = 0;

      for (int i = 0; i < n; i++) {
         int m = Integer.parseInt(birthdays[i].substring(0,
               birthdays[i].indexOf("/")));
         int d = Integer.parseInt(birthdays[i].substring(
               birthdays[i].indexOf("/") + 1, birthdays[i].indexOf(" ")));
         if (m < firstm || (m == firstm && d <= firstd)) {
            firstm = m;
            firstd = d;
            firstindex = i;
         }
         if (m > datem || (m == datem && d >= dated)) {
            found = true;
            if (m < minm || (m == minm && d <= mind)) {
               minm = m;
               mind = d;
               minindex = i;
            }
         }
      }
      if (found)
         return birthdays[minindex].substring(0,
               birthdays[minindex].indexOf(" "));
      else
         return birthdays[firstindex].substring(0,
               birthdays[firstindex].indexOf(" "));
   }
}
