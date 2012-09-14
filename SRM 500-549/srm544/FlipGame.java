package srm544;

public class FlipGame {
	private static int N;
	private static int M;
	private static boolean[][] grid;

	public static int minOperations(String[] board) {
		N = board.length;
		M = board[0].length();
		grid = new boolean[N][M];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				if (board[i].charAt(j) == '1')
					grid[i][j] = true;
		int count = 0;

		while (true) {
			int limit = -1;
			boolean ok = true;
			for (int i = 0; i < N; i++) {
				int to;
				boolean found = false;
				for (to = M - 1; to >= 0; to--)
					if (grid[i][to]) {
						found = true;
						break;
					}
				if (found) {
					ok = false;
					limit = Math.max(limit, to);
				}
				for (int j = 0; j <= limit; j++)
					grid[i][j] = !grid[i][j];
			}
			if (ok)
				break;
			else
				count++;
		}

		return count;
	}
}
