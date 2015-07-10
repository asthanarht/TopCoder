public class FallingSand {

	public String[] simulate(String[] board) {
		int n = board.length, m = board[0].length();
		char[][] grid = new char[n][m];
		for (int i = 0; i < n; i++)
			grid[i] = board[i].toCharArray();
		for (int c = 0; c < m; c++)
			for (int r = n - 1; r >= 0; r--)
				if (grid[r][c] == 'o') {
					int i = r;
					while (i + 1 < n && grid[i + 1][c] == '.') {
						grid[i + 1][c] = 'o';
						grid[i][c] = '.';
						i++;
					}
				}
		String[] res = new String[n];
		for (int i = 0; i < n; i++)
			res[i] = String.valueOf(grid[i]);
		return res;
	}

}
