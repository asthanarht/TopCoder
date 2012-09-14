package srm515;

public class RotatedClock {
   public String getEarliest(int hourHand, int minuteHand) {
      int h = (hourHand % 30) * 12;
      int min = Integer.MAX_VALUE;
      for (int i = 0; i < 12; i++) {
         int m = minuteHand + 30 * i;
         if (m % 360 == h) {
            int t = ((hourHand / 30 + i) % 12) * 60 + (m % 360) / 6;
            if (t <= min)
               min = t;
         }
      }
      if (min == Integer.MAX_VALUE)
         return "";

      int hi = min / 60;
      String hs = "";
      if (hi < 10)
         hs = "0" + hi;
      else
         hs = "" + hi;

      int mi = min % 60;
      String ms = "";
      if (mi < 10)
         ms = "0" + mi;
      else
         ms = "" + mi;

      return hs + ":" + ms;
   }
}