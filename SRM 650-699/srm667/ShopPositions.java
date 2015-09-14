public class ShopPositions {

	public int maxProfit(int n, int m, int[] c) {
		int[][] dp = new int[m + 1][m + 1];
		for (int x = 0; x < n; x++) {
			int[][] next = new int[m + 1][m + 1];
			int imax = (x == 0 ? 0 : m);
			int kmax = (x == n - 1 ? 0 : m);
			for (int i = 0; i <= imax; i++)
				for (int j = 0; j <= m; j++)
					for (int k = 0; k <= kmax; k++)
						next[j][k] = Math.max(next[j][k], dp[i][j]
								+ (j == 0 ? 0 : j
										* c[x * 3 * m + i + j + k - 1]));
			dp = next;
		}
		int res = 0;
		for (int i = 0; i <= m; i++)
			for (int j = 0; j <= m; j++)
				res = Math.max(res, dp[i][j]);
		return res;
	}

}
