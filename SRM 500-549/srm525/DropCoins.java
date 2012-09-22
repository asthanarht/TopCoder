package srm525;

public class DropCoins {
    public int getMinimum(String[] board, int K) {
        int min = Integer.MAX_VALUE;
        int m = board.length;
        int n = board[0].length();
        boolean[][] coin = new boolean[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (board[i].charAt(j) == 'o')
                    coin[i][j] = true;
                else
                    coin[i][j] = false;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                for (int p = i; p < m; p++)
                    for (int q = j; q < n; q++) {
                        int count = 0;
                        for (int a = i; a <= p; a++)
                            for (int b = j; b <= q; b++)
                                if (coin[a][b])
                                    count++;
                        if (count == K) {
                            int down = m - p - 1;
                            int right = n - q - 1;
                            int move = i + j + down + right;
                            move += i > down ? down : i;
                            move += j > right ? right : j;
                            if (move < min)
                                min = move;
                        }
                    }
        if (min == Integer.MAX_VALUE)
            return -1;
        else
            return min;
    }
}
