public class TheMatrix {
	public int MaxArea(String[] board) {
		int m = board.length, n = board[0].length();
		int max = 1;
		int[][][] dp = new int[m][n][n];
		int[][] cntc = new int[m][n];
		int[][] cntr = new int[m][n];
		for (int row = 0; row < m; row++) {
			for (int col = 0; col < n; col++) {
				if (row > 0
				        && board[row].charAt(col) != board[row - 1].charAt(col))
					cntc[row][col] = cntc[row - 1][col] + 1;
				else
					cntc[row][col] = 1;
				if (col > 0
				        && board[row].charAt(col) != board[row].charAt(col - 1))
					cntr[row][col] = cntr[row][col - 1] + 1;
				else
					cntr[row][col] = 1;
				for (int from = 0; from <= col; from++) {
					int width = col - from + 1;
					if (width > cntr[row][col])
						continue;
					dp[row][from][col] = 1;
					max = Math.max(max, width);
					if (cntc[row][col] > 1)
						dp[row][from][col] = dp[row - 1][from][col] + 1;
					max = Math.max(max, width * dp[row][from][col]);
				}
			}
		}
		return max;
	}
}
