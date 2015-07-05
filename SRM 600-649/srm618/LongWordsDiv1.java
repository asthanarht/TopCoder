public class LongWordsDiv1 {

	public int count(int n) {
		long MOD = 1000000007;
		// pattern
		long[] dp = new long[5001];
		dp[1] = 1;
		dp[2] = 1;
		for (int i = 3; i <= n; i++) {
			// A + f(n-1) + A
			dp[i] = dp[i - 1];
			// A + f(j) + A + f(i-j-1) + A
			for (int j = 1; j <= i - 2; j++) {
				dp[i] += dp[j] * dp[i - j - 1];
				dp[i] %= MOD;
			}
		}
		// pattern * permutation(n letter)
		for (int i = 2; i <= n; i++) {
			dp[n] *= i;
			dp[n] %= MOD;
		}
		return (int) dp[n];
	}

}