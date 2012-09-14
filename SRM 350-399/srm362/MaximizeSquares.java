package srm362;

public class MaximizeSquares {
   public int squareCount(int N) {
      int count = 0;
      int side = (int) Math.sqrt(N); // make a square lattice as big as possible
      count += count(side);
      int remain = N - side * side;
      // remaining points can be more than side number
      if (remain > side) {
         count += append(side);
         remain -= side;
      }
      count += append(remain);
      return count;
   }

   /*
    * count how many squares can be made by a square lattice with sides have
    * side points.
    */
   private int count(int side) {
      int count = 0;
      for (int len = 1; len < side; len++)
         count += (side - len) * (side - len);
      return count;
   }

   /*
    * count how many squares can be made if side points be appended to a side of
    * a square lattice.
    */
   private int append(int side) {
      int count = 0;
      for (int len = 1; len < side; len++)
         count += side - len;
      return count;
   }
}
