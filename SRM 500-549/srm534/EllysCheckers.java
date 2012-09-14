package srm534;

public class EllysCheckers {
   public static String getWinner(String board) {
      int count = 0;
      for (int i = 0; i < board.length() - 1; i++)
         if (board.charAt(i) == 'o')
            count += board.length() - 1 - i;
      return count % 2 == 1 ? "YES" : "NO";
   }
}
