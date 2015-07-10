import java.util.Arrays;

public class ExactTree {

	public int getTree(int n, int m, int r) {
		int[][] dp = new int[n + 1][m];
		for (int i = 0; i <= n; i++)
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		dp[1][0] = 0;
		for (int i = 2; i <= n; i++) {
			for (int p1 = 1; p1 < i; p1++) {
				int p2 = i - p1;
				for (int j1 = 0; j1 < m; j1++)
					if (dp[p1][j1] != Integer.MAX_VALUE)
						for (int j2 = 0; j2 < m; j2++)
							if (dp[p2][j2] != Integer.MAX_VALUE) {
								int st = dp[p1][j1] + dp[p2][j2] + p1
										* (n - p1);
								int newj = st % m;
								dp[i][newj] = Math.min(dp[i][newj], st);
							}
			}
		}
		return dp[n][r] == Integer.MAX_VALUE ? -1 : dp[n][r];
	}

}
