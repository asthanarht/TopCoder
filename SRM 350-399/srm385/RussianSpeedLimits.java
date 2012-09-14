package srm385;

public class RussianSpeedLimits {
   public int getCurrentLimit(String[] signs) {
      if (!signs[signs.length - 1].equals("city")
            && !signs[signs.length - 1].equals("default"))
         return Integer.parseInt(signs[signs.length - 1]);
      int city = 0;
      for (int i = 0; i < signs.length; i++)
         if (signs[i].equals("city"))
            city++;
      if (city % 2 == 0)
         return 60;
      return 90;
   }
}
