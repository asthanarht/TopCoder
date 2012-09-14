package srm519;

public class WhichDay {
   public String getDay(String[] notOnThisDay) {
      int total = 1 + 2 + 3 + 4 + 5 + 6;
      for (int i = 0; i < notOnThisDay.length; i++)
         total -= getIndex(notOnThisDay[i]);
      return getName(total);
   }

   private int getIndex(String day) {
      if (day.equals("Sunday"))
         return 0;
      else if (day.equals("Monday"))
         return 1;
      else if (day.equals("Tuesday"))
         return 2;
      else if (day.equals("Wednesday"))
         return 3;
      else if (day.equals("Thursday"))
         return 4;
      else if (day.equals("Friday"))
         return 5;
      else if (day.equals("Saturday"))
         return 6;
      return 0;
   }

   private String getName(int index) {
      switch (index) {
      case 0:
         return "Sunday";
      case 1:
         return "Monday";
      case 2:
         return "Tuesday";
      case 3:
         return "Wednesday";
      case 4:
         return "Thursday";
      case 5:
         return "Friday";
      case 6:
         return "Saturday";
      }
      return null;
   }
}
