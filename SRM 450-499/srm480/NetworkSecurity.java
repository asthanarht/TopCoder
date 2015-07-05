public class NetworkSecurity {
    public int secureNetwork(String[] clientCable, String[] serverCable) {
        int c = clientCable.length;
        int s = serverCable[0].length();
        boolean[][] cc = new boolean[c][c];
        for (int i = 0; i < c; i++)
            for (int j = 0; j < c; j++)
                if (clientCable[i].charAt(j) == 'Y')
                    cc[i][j] = true;
        int[][] sc = new int[c][s];
        for (int i = 0; i < c; i++)
            for (int j = 0; j < s; j++)
                if (serverCable[i].charAt(j) == 'Y')
                    sc[i][j] = 1;
        for (int k = 0; k < c; k++)
            for (int i = 0; i < c; i++)
                for (int j = 0; j < c; j++)
                    if (cc[i][k] && cc[k][j])
                        cc[i][j] = true;
        for (int k = 0; k < c; k++)
            for (int i = 0; i < c; i++)
                for (int j = 0; j < s; j++)
                    if (cc[i][k] && sc[k][j] > 0)
                        sc[i][j] = 2;
        int count = 0;
        for (int i = 0; i < c; i++)
            for (int j = 0; j < s; j++)
                if (sc[i][j] == 1)
                    count++;
        return count;
    }
}
