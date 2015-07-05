public class PalindromicSubstringsDiv1 {
	public double expectedPalindromes(String[] S1, String[] S2) {
		String s = "";
		for (int i = 0; i < S1.length; i++)
			s += S1[i];
		for (int i = 0; i < S2.length; i++)
			s += S2[i];
		int n = s.length();
		double[][] dp = new double[n + 1][n]; // [len][from]
		double res = 0;
		for (int i = 0; i < n; i++) {
			dp[0][i] = 1;
			dp[1][i] = 1;
			res += 1;
		}
		for (int len = 2; len <= n; len++)
			for (int from = 0; from <= n - len; from++) {
				int to = from + len - 1;
				if (s.charAt(from) == '?' || s.charAt(to) == '?')
					dp[len][from] = dp[len - 2][from + 1] / 26;
				else if (s.charAt(from) == s.charAt(to))
					dp[len][from] = dp[len - 2][from + 1];
				else
					dp[len][from] = 0;
				res += dp[len][from];
			}
		return res;
	}
}
