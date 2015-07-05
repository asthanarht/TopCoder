public class WaitingForBus {

	public double whenWillBusArrive(int[] time, int[] prob, int s) {
		double[] dp = new double[200001];
		int n = prob.length;
		dp[0] = 1;
		for (int i = 0; i < s; i++)
			if (dp[i] > 0)
				for (int j = 0; j < n; j++)
					dp[i + time[j]] += dp[i] * prob[j] / 100;
		double res = 0;
		for (int i = s; i <= 200000; i++)
			if (dp[i] > 0)
				res += (i - s) * dp[i];
		return res;
	}

}
