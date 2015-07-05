public class ElephantDrinkingEasy {
	public int maxElephants(String[] map) {
		int n = map.length, N = n + 2;
		int res = 0;
		for (int side = 0; side < 2; side++) {
			int[][] grid = new int[N][N];
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++) {
					if (side == 0)
						grid[i + 1][j + 1] = map[i].charAt(j) == 'Y' ? 1 : 0;
					else
						grid[i + 1][j + 1] = map[j].charAt(i) == 'Y' ? 1 : 0;
				}
			int[][] up = new int[N][N], down = new int[N][N];
			for (int j = 1; j <= n; j++) {
				for (int i = 1; i <= n + 1; i++)
					up[i][j] = Math.max(up[i - 1][j], grid[i][j]);
				for (int i = n; i >= 0; i--)
					down[i][j] = Math.max(down[i + 1][j], grid[i][j]);
			}
			int[][] left = new int[N][N], right = new int[N][N];
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n + 1; j++)
					left[i][j] = Math.max(left[i][j - 1], grid[i][j]);
				for (int j = n; j >= 0; j--)
					right[i][j] = Math.max(right[i][j + 1], grid[i][j]);
			}
			int[][] upleft = new int[N][N];
			for (int i = 1; i <= n + 1; i++)
				for (int j = 1; j <= n + 1; j++)
					upleft[i][j] = Math.max(up[i][j] + upleft[i][j - 1],
					        left[i][j] + upleft[i - 1][j]);
			int[][] upright = new int[N][N];
			for (int i = 1; i <= n + 1; i++)
				for (int j = n; j >= 0; j--)
					upright[i][j] = Math.max(up[i][j] + upright[i][j + 1],
					        right[i][j] + upright[i - 1][j]);
			int[][] downleft = new int[N][N];
			for (int i = n; i >= 0; i--)
				for (int j = 1; j <= n + 1; j++)
					downleft[i][j] = Math.max(down[i][j] + downleft[i][j - 1],
					        left[i][j] + downleft[i + 1][j]);
			int[][] downright = new int[N][N];
			for (int i = n; i >= 0; i--)
				for (int j = n; j >= 0; j--)
					downright[i][j] = Math.max(
					        down[i][j] + downright[i][j + 1], right[i][j]
					                + downright[i + 1][j]);
			int[] row = new int[N];
			for (int i = 1; i <= n; i++)
				for (int j = 1; j <= n + 1; j++)
					row[i] = Math.max(row[i], left[i][j - 1] + right[i][j]);
			for (int r1 = 1; r1 <= n; r1++)
				for (int r2 = r1; r2 <= n; r2++) {
					int sum = 0;
					for (int i = r1; i <= r2; i++)
						sum += row[i];
					int maxup = 0;
					for (int j = 1; j <= n + 1; j++)
						maxup = Math.max(maxup, upleft[r1 - 1][j - 1]
						        + upright[r1 - 1][j]);
					int maxdown = 0;
					for (int j = 1; j <= n + 1; j++)
						maxdown = Math.max(maxdown, downleft[r2 + 1][j - 1]
						        + downright[r2 + 1][j]);
					sum += maxup + maxdown;
					res = Math.max(res, sum);
				}
			for (int r1 = 0; r1 <= n; r1++)
				for (int r2 = r1 + 1; r2 <= n + 1; r2++)
					for (int c1 = 0; c1 <= n; c1++)
						for (int c2 = c1 + 1; c2 <= n + 1; c2++) {
							int sum = upleft[r1][c2 - 1] + upright[r2 - 1][c2]
							        + downleft[r1 + 1][c1]
							        + downright[r2][c1 + 1];
							res = Math.max(res, sum);
						}
		}
		return res;
	}
}
