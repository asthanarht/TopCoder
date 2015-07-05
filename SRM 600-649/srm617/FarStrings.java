public class FarStrings {

	public String[] find(String t) {
		int n = t.length();
		String[] res = new String[n];
		for (int i = 0; i < n; i++) {
			int d = i + 1;
			char[] ans = new char[n];
			int[][] dp = new int[n + 1][n + 1];
			for (int r = 0; r <= n; r++)
				dp[r][0] = r;
			for (int c = 0; c <= n; c++)
				dp[0][c] = c;
			for (int c = 1; c <= n; c++) {
				for (char ch = 'a'; ch <= 'z'; ch++) {
					boolean ok = true;
					for (int r = 1; r <= n; r++) {
						dp[r][c] = Math.min(dp[r - 1][c], dp[r][c - 1]) + 1;
						if (ch == t.charAt(r - 1))
							dp[r][c] = Math.min(dp[r][c], dp[r - 1][c - 1]);
						else
							dp[r][c] = Math.min(dp[r][c], dp[r - 1][c - 1] + 1);
						if (dp[r][c] + Math.max(n - r, n - c) < d)
							ok = false;
					}
					if (ok && dp[c][c] <= d && dp[c][c] + (n - c) >= d
							&& dp[n][c] + (n - c) >= d) {
						ans[c - 1] = ch;
						break;
					}
				}
			}
			res[i] = String.valueOf(ans);
		}
		return res;
	}

}
