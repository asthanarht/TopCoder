package srm315;

public class RosePetals {
   public int getScore(int[] dice) {
      int[] ps = { 0, 0, 0, 2, 0, 4, 0 };
      int pedal = 0;
      for (int i = 0; i < dice.length; i++)
         pedal += ps[dice[i]];
      return pedal;
   }
}
