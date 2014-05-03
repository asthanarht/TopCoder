package r1B;

public class WolvesAndSheep {

	public int getmin(String[] field) {
		int m = field.length, n = field[0].length();
		int min = m + n;
		next: for (int mask = 0; mask < (1 << m); mask++)
			if ((mask & (1 << (m - 1))) > 0) {
				char[][] dp = new char[m][n];
				for (int i = 0; i < m; i++) {
					for (int j = 0; j < n; j++)
						if (field[i].charAt(j) != '.') {
							if (dp[i][j] == '\0')
								dp[i][j] = field[i].charAt(j);
							else if (dp[i][j] != field[i].charAt(j))
								continue next;
						}
					if ((mask & (1 << i)) == 0)
						for (int j = 0; j < n; j++)
							dp[i + 1][j] = dp[i][j];
				}
				int cnt = Integer.bitCount(mask) - 1;
				char[] row = new char[m];
				for (int j = 0; j < n; j++) {
					boolean ok = true;
					for (int i = 0; i < m; i++)
						if ((mask & (1 << i)) > 0)
							if (row[i] != '\0' && dp[i][j] != '\0'
									&& row[i] != dp[i][j])
								ok = false;
					if (ok) {
						for (int i = 0; i < m; i++)
							if ((mask & (1 << i)) > 0)
								if (dp[i][j] != '\0')
									row[i] = dp[i][j];
					} else {
						cnt++;
						for (int i = 0; i < m; i++)
							if ((mask & (1 << i)) > 0)
								row[i] = dp[i][j];
					}
				}
				min = Math.min(min, cnt);
			}
		return min;
	}
}
