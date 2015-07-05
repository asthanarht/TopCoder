public class RandomPancakeStack {

	public double expectedDeliciousness(int[] d) {
		int n = d.length;
		// [already selected][i-th pancake] probability to be selected
		double[][] dp = new double[n][n];
		for (int i = 0; i < n; i++)
			dp[0][i] = 1.0 / n;
		for (int i = 1; i < n; i++)
			for (int j = 0; j < n - i; j++)
				for (int k = j + 1; k < n; k++)
					dp[i][j] += dp[i - 1][k] / (n - i);
		double sum = 0;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				sum += dp[i][j] * d[j];
		return sum;
	}

}
