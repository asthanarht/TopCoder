public class CollectingRiders {
    private int m;
    private int n;
    private int c;
    private int[] xc = { 1, 1, 2, 2, -1, -1, -2, -2 };
    private int[] yc = { 2, -2, 1, -1, 2, -2, 1, -1 };
    private int[][] floyd;

    public int minimalMoves(String[] board) {
        m = board.length;
        n = board[0].length();
        c = m * n;

        floyd = new int[c][c];
        for (int i = 0; i < c; i++)
            for (int j = 0; j < c; j++)
                floyd[i][j] = -1;

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                for (int k = 0; k < 8; k++) {
                    int x = i + xc[k];
                    int y = j + yc[k];
                    if (x >= 0 && y >= 0 && x < m && y < n) {
                        int index1 = index(i, j);
                        int index2 = index(x, y);
                        floyd[index1][index2] = 1;
                        floyd[index2][index1] = 1;
                    }
                }

        for (int k = 0; k < c; k++)
            for (int i = 0; i < c; i++)
                for (int j = 0; j < c; j++)
                    if (floyd[i][k] != -1
                            && floyd[k][j] != -1
                            && (floyd[i][j] == -1 || floyd[i][j] > floyd[i][k]
                                    + floyd[k][j]))
                        floyd[i][j] = floyd[i][k] + floyd[k][j];

        int minMove = Integer.MAX_VALUE;
        next: for (int loc = 0; loc < c; loc++) {
            int move = 0;
            for (int i = 0; i < m; i++)
                for (int j = 0; j < n; j++) {
                    char c = board[i].charAt(j);
                    if (c == '.')
                        continue;
                    int k = c - '0';
                    int index = index(i, j);
                    if (index == loc)
                        continue;
                    int required = floyd[loc][index];
                    if (required == -1)
                        continue next;
                    move += required / k;
                    move += required % k > 0 ? 1 : 0;
                }
            minMove = Math.min(minMove, move);
        }

        return minMove == Integer.MAX_VALUE ? -1 : minMove;
    }

    private int index(int i, int j) {
        return i * n + j;
    }
}
