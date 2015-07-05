public class SquareScores {

	public double calcexpectation(int[] p, String s) {
		int n = s.length();
		double[] pr = new double[26];
		for (int i = 0; i < p.length; i++)
			pr[i] = p[i] / 100.0;
		char[] str = s.toCharArray();
		double[][][] dp = new double[n][n][26];
		for (int i = 0; i < n; i++)
			if (str[i] == '?')
				for (int le = 0; le < 26; le++)
					dp[i][i][le] = pr[le];
			else
				dp[i][i][str[i] - 'a'] = 1;
		double res = n;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++)
				for (int le = 0; le < 26; le++) {
					dp[i][j][le] += dp[i][j - 1][le] * dp[j][j][le];
					res += dp[i][j][le];
				}
		}
		return res;
	}

}
