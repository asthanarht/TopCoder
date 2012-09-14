package srm410;

public class AddElectricalWires {
   public int maxNewWires(String[] wires, int[] gridConnections) {
      int n = wires.length;
      int[][] map = new int[n][n];
      for (int i = 0; i < n; i++)
         for (int j = 0; j < n; j++) {
            if (wires[i].charAt(j) == '0')
               map[i][j] = 0;
            else
               map[i][j] = 1;
         }

      boolean[] b = new boolean[n];
      for (int i = 0; i < gridConnections.length; i++) {
         int index = gridConnections[i];
         b[index] = true;
      }

      int count = 0;
      for (int k = 0; k < n; k++)
         for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
               if (i != j && map[i][k] == 1 && map[k][j] == 1)
                  if (b[i] && b[j])
                     ;
                  else {
                     if (map[i][j] == 0) {
                        count++;
                        map[i][j] = 1;
                     }
                  }

      int max = 0;
      int maxindex = 0;
      for (int i = 0; i < n; i++)
         if (b[i]) {
            int check = 0;
            for (int j = 0; j < n; j++)
               if (map[i][j] == 1)
                  check++;
            if (check > max) {
               max = check;
               maxindex = i;
            }
         }

      if (max == 0)
         maxindex = gridConnections[0];
      for (int i = 0; i < n; i++)
         if (!b[i]) {
            int check = 0;
            for (int j = 0; j < n; j++)
               if (map[i][j] == 1 && b[j])
                  check++;
            if (check == 0) {
               map[i][maxindex] = 1;
               map[maxindex][i] = 1;
               count += 2;
            }
         }

      for (int k = 0; k < n; k++)
         for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
               if (i != j && map[i][k] == 1 && map[k][j] == 1)
                  if (b[i] && b[j])
                     ;
                  else {
                     if (map[i][j] == 0) {
                        count++;
                        map[i][j] = 1;
                     }
                  }

      return count / 2;
   }
}
