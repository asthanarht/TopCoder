public class EllysFigurines {
    public int getMoves(String[] board, int R, int C) {
        int n = board.length, m = board[0].length();
        boolean[][] grid = new boolean[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (board[i].charAt(j) == 'X')
                    grid[i][j] = true;
        int result = n;
        for (int mask = 0; mask < (1 << n); mask++) {
            int move = 0;
            boolean[][] grid2 = new boolean[n][m];
            for (int i = 0; i < n;)
                if ((mask & (1 << i)) > 0) {
                    i += R;
                    move++;
                }
                else {
                    for (int j = 0; j < m; j++)
                        grid2[i][j] = grid[i][j];
                    i++;
                }
            for (int j = 0; j < m;) {
                boolean found = false;
                for (int i = 0; i < n; i++)
                    if (grid2[i][j])
                        found = true;
                if (!found)
                    j++;
                else {
                    move++;
                    j += C;
                }
            }
            result = Math.min(result, move);
        }
        return result;
    }
}
