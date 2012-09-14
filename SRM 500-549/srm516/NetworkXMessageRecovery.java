package srm516;

public class NetworkXMessageRecovery {
   public String recover(String[] messages) {
      boolean[] V = new boolean[52];
      boolean[][] E = new boolean[52][52];
      for (String s : messages)
         for (int i = 0; i < s.length(); i++) {
            V[getIndex(s.charAt(i))] = true;
            if (i + 1 < s.length())
               E[getIndex(s.charAt(i))][getIndex(s.charAt(i + 1))] = true;
         }
      String res = "";
      for (int n = 0; n < 52; n++)
         for (int j = 0; j < 52; j++)
            if (V[j]) {
               boolean ok = true;
               // check if any char need to go before V[j]
               for (int i = 0; i < 52; i++)
                  if (V[i] && E[i][j]) {
                     ok = false;
                     break;
                  }
               if (ok) {
                  res += getChar(j);
                  V[j] = false;
                  break;
               }
            }
      return res;
   }

   private int getIndex(char c) {
      if (Character.isUpperCase(c))
         return c - 'A';
      return c - 'a' + 26;
   }

   private char getChar(int index) {
      if (index < 26)
         return (char) ('A' + index);
      return (char) ('a' + (index - 26));
   }
}
