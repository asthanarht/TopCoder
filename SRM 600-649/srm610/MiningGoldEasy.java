package srm610;

public class MiningGoldEasy {

	public int GetMaximumGold(int N, int M, int[] event_i, int[] event_j) {
		int n = event_i.length;
		int[][][] dp = new int[n][n][n]; // max[k][ith event_i][jth event_j]
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				dp[0][i][j] = M + N - Math.abs(event_i[0] - event_i[i])
				        - Math.abs(event_j[0] - event_j[j]);
		for (int cur = 0; cur < n - 1; cur++)
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					for (int to = 0; to < n; to++) {
						int ito = dp[cur][i][j] + M + N
						        - Math.abs(event_i[cur + 1] - event_i[to])
						        - Math.abs(event_j[cur + 1] - event_j[j]);
						dp[cur + 1][to][j] = Math.max(dp[cur + 1][to][j], ito);
						int jto = dp[cur][i][j] + M + N
						        - Math.abs(event_i[cur + 1] - event_i[i])
						        - Math.abs(event_j[cur + 1] - event_j[to]);
						dp[cur + 1][i][to] = Math.max(dp[cur + 1][i][to], jto);
					}
		int max = 0;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				max = Math.max(max, dp[n - 1][i][j]);
		return max;
	}

}
