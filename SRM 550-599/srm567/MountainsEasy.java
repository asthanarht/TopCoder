import java.util.Arrays;

public class MountainsEasy {

	public int countPlacements(String[] picture, int N) {
		int n = picture.length, m = picture[0].length();
		int must = 0, might = 0;
		boolean[][] used = new boolean[n][m];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				if (picture[i].charAt(j) == 'X') {
					if (used[i][j])
						might++;
					else {
						must++;
						for (int c = 0; c < m; c++)
							for (int r = i + Math.abs(j - c); r < n; r++)
								used[r][c] = true;
					}
				}
		for (int i = 0; i <= 50; i++)
			Arrays.fill(dp[i], -1);
		return solve(must, might, N);
	}

	private int mod = 1000000009;
	private int[][] dp = new int[51][51];

	private int solve(int must, int might, int n) {
		if (dp[must][n] == -1) {
			dp[must][n] = 0;
			if (n == 0)
				dp[must][n] = (must == 0 ? 1 : 0);
			else {
				// pick one that is not necessary
				long sub = solve(must, might, n - 1);
				dp[must][n] += (might * sub) % mod;
				// pick one that must be included
				if (must > 0) {
					sub = solve(must - 1, might + 1, n - 1);
					dp[must][n] += (must * sub) % mod;
				}
				dp[must][n] %= mod;
			}
		}
		return dp[must][n];
	}
}
