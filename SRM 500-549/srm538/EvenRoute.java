package srm538;

public class EvenRoute {
   public String isItPossible(int[] x, int[] y, int wantedParity) {
      for (int i = 0; i < x.length; i++)
         if (Math.abs(x[i]) + Math.abs(y[i]) % 2 == wantedParity)
            return "CAN";
      return "CANNOT";
   }
}
