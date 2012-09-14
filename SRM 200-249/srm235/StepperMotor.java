package srm235;

public class StepperMotor {
   public static int rotateToNearest(int n, int current, int[] target) {
      int min = n;
      long cur = ((long) current % n + n) % n;
      for (int i = 0; i < target.length; i++) {
         long tar = (((long) target[i]) % n + n) % n;
         int step1 = (int) ((tar - cur + (long) n) % n);
         int step2 = -(int) ((cur - tar + (long) n) % n);
         min = Math.abs(min) >= step1 ? step1 : min;
         min = Math.abs(step2) < Math.abs(min) ? step2 : min;
      }
      return min;
   }
}
