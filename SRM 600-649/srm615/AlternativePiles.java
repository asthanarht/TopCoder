package srm615;

public class AlternativePiles {
	private static final int MOD = 1000000007;

	public int count(String C, int M) {
		int[][] dp = new int[M + 1][M]; // [r-g][r % M]
		dp[0][0] = 1;
		for (char c : C.toCharArray()) {
			int[][] next = new int[M + 1][M];
			for (int d = 0; d <= M; d++)
				for (int r = 0; r < M; r++)
					if (dp[d][r] > 0) {
						if (c == 'R' || c == 'W') {
							int rp = (r + 1) % M;
							if (d < M)
								next[d + 1][rp] = add(next[d + 1][rp], dp[d][r]);
						}
						if (c == 'G' || c == 'W') {
							if (d > 0)
								next[d - 1][r] = add(next[d - 1][r], dp[d][r]);
						}
						if (c == 'B' || c == 'W') {
							next[d][r] = add(next[d][r], dp[d][r]);
						}
					}
			dp = next;
		}
		return dp[0][0];
	}

	private int add(int n1, int n2) {
		int sum = n1 + n2;
		return sum >= MOD ? sum - MOD : sum;
	}

}
