package srm200;

public class WindowManager {
   public String[] screen(int height, int width, String[] windows) {
      char[][] screen = new char[height][width];
      for (int i = 0; i < screen.length; i++)
         for (int j = 0; j < screen[i].length; j++)
            screen[i][j] = ' ';
      for (int i = 0; i < windows.length; i++)
         screen = window(screen, windows[i]);
      String[] result = new String[screen.length];
      for (int i = 0; i < screen.length; i++) {
         result[i] = "";
         for (int j = 0; j < screen[i].length; j++)
            result[i] += screen[i][j];
      }
      return result;
   }

   private char[][] window(char[][] screen, String window) {
      String[] info = window.split(" ");
      int tlv = Integer.parseInt(info[0]);
      int tlh = Integer.parseInt(info[1]);
      int vs = Integer.parseInt(info[2]);
      int hs = Integer.parseInt(info[3]);
      int tlvvs = tlv + vs - 1;
      int tlhhs = tlh + hs - 1;
      char fill = info[4].charAt(0);

      for (int i = 0; i < screen.length; i++)
         for (int j = 0; j < screen[i].length; j++) {
            if ((i == tlv || i == tlvvs) && (j == tlh || j == tlhhs))
               screen[i][j] = '+';
            else if ((i == tlv || i == tlvvs) && (j > tlh && j < tlhhs))
               screen[i][j] = '-';
            else if ((i > tlv && i < tlvvs) && (j == tlh || j == tlhhs))
               screen[i][j] = '|';
            else if ((i > tlv && i < tlvvs) && (j > tlh && j < tlhhs))
               screen[i][j] = fill;
         }

      return screen;
   }
}
