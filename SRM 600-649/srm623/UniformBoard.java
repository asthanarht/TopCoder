public class UniformBoard {

    public int getBoard(String[] board, int K) {
        int n = board.length;
        char[][] grid = new char[n][n];
        for (int i = 0; i < n; i++)
            grid[i] = board[i].toCharArray();

        int totalA = 0;
        boolean found = false;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (grid[i][j] == 'A')
                    totalA++;
                else if (grid[i][j] == '.')
                    found = true;

        int res = 0;
        for (int r1 = 0; r1 < n; r1++)
            for (int r2 = r1 + 1; r2 <= n; r2++)
                for (int c1 = 0; c1 < n; c1++)
                    for (int c2 = c1 + 1; c2 <= n; c2++) {
                        int size = (r2 - r1) * (c2 - c1);
                        if (totalA < size)
                            continue;
                        int cntA = 0, cntP = 0;
                        for (int r = r1; r < r2; r++)
                            for (int c = c1; c < c2; c++)
                                if (grid[r][c] == 'A')
                                    cntA++;
                                else if (grid[r][c] == 'P')
                                    cntP++;
                        int k = size - cntA + cntP;
                        if (k == 0 || (found && k <= K))
                            res = Math.max(res, size);
                    }
        return res;
    }

}
