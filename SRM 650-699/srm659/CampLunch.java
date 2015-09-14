public class CampLunch {

	public int count(int N, int M, String[] a) {
		int[] dp = new int[1 << M];
		dp[(1 << M) - 1] = 1;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++) {
				next = new int[1 << M];
				for (int mask = 0; mask < (1 << M); mask++) {
					int id = a[i].charAt(j) - 'A';
					if ((mask & (1 << id)) > 0) { // served on previous day
						// reserved for future double lunch
						update(mask ^ (1 << id), dp[mask]);
						// single lunch
						update(mask, dp[mask]);
						// double lunch with another people
						if (j > 0) {
							int idp = a[i].charAt(j - 1) - 'A';
							// the other people not served
							if ((mask & (1 << idp)) == 0)
								update(mask | (1 << idp), dp[mask]);
						}
					} else { // not served on previous day
						// double lunch for one person in two consecutive days
						update(mask | (1 << id), dp[mask]);
					}
				}
				dp = next;
			}
		return dp[(1 << M) - 1];
	}

	private int[] next;
	private int mod = 1000000007;

	private void update(int i, int add) {
		next[i] += add;
		if (next[i] >= mod)
			next[i] -= mod;
	}

}
