public class TorusSailing {

	public double expectedTime(int N, int M, int goalX, int goalY) {
		double[][] dp = new double[N][M];
		double pre = 0;
		while (true) {
			for (int i = 0; i < N; i++)
				for (int j = 0; j < M; j++)
					if (i != 0 || j != 0)
						dp[i][j] = (dp[(i + N - 1) % N][j] + dp[i][(j + M - 1)
								% M]) * 0.5 + 1;
			if (Math.abs(dp[goalX][goalY] - pre) < 1e-9)
				break;
			pre = dp[goalX][goalY];
		}
		return dp[goalX][goalY];
	}

}
