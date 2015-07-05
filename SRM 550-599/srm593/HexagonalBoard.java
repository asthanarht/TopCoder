public class HexagonalBoard {
	public int[] xc = { -1, -1, 0, 1, 1, 0 };
	public int[] yc = { 0, 1, 1, 0, -1, -1 };
	public int min = 0;
	public int n;
	public int[][] color;

	public int minColors(String[] board) {
		n = board.length;
		color = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				color[i][j] = -1;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (board[i].charAt(j) == 'X')
					check(board, i, j, 0);
		return min;
	}

	public void check(String[] board, int x, int y, int c) {
		if (color[x][y] == -1) {
			color[x][y] = c;
			min = Math.max(min, 1);
			for (int i = 0; i < 6; i++) {
				int xnew = x + xc[i];
				int ynew = y + yc[i];
				if (xnew < 0 || xnew >= n || ynew < 0 || ynew >= n)
					continue;
				if (board[xnew].charAt(ynew) == 'X') {
					min = Math.max(min, 2);
					check(board, xnew, ynew, 1 - c);
					if (color[xnew][ynew] == c)
						min = 3;
				}
			}
		}
	}
}
