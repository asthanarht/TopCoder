package srm522;

public class CorrectMultiplicationTwo {
   public int getMinimum(int a, int b, int c) {
      int min = a + b + c;
      for (int A = 1; A <= 1000000; A++)
         for (int B = 1; B <= 4000000 / A; B++)
            min = Math.min(min,
                  Math.abs(A - a) + Math.abs(B - b) + Math.abs(A * B - c));
      return min;
   }
}
