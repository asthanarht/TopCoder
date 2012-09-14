package srm325;

public class FenceRepairing {
	public double calculateCost(String[] boards) {
		String bs = boards[0];
		for (int i = 1; i < boards.length; i++)
			bs += boards[i];
		int n = bs.length();
		double[] dp = new double[n]; // min[to]
		if (bs.charAt(0) == 'X')
			dp[0] = 1;
		for (int i = 1; i < n; i++) {
			if (bs.charAt(i) == '.') {
				dp[i] = dp[i - 1];
				continue;
			}
			dp[i] = dp[i - 1] + 1;
			for (int j = i - 1; j > 0; j--)
				if (bs.charAt(j) == 'X')
					dp[i] = Math.min(dp[i], dp[j - 1] + Math.sqrt(i - j + 1));
			dp[i] = Math.min(dp[i], Math.sqrt(i + 1));
		}
		return dp[n - 1];
	}
}
