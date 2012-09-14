package srm517;

public class MonochromaticBoard {
   public int theMin(String[] board) {
      int r = board.length;
      int c = board[0].length();
      int total = 0;
      int count;
      for (int i = 0; i < r; i++) {
         for (count = 0; count < c; count++)
            if (board[i].charAt(count) == 'W')
               break;
         if (count == c)
            total++;
      }
      if (total != r)
         for (int j = 0; j < c; j++) {
            for (count = 0; count < r; count++)
               if (board[count].charAt(j) == 'W')
                  break;
            if (count == r)
               total++;
         }
      else
         return r < c ? r : c;
      return total;
   }
}