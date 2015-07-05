public class MergeStrings {

	public String getmin(String S, String A, String B) {
		char[] s = S.toCharArray(), a = A.toCharArray(), b = B.toCharArray();
		int ns = S.length(), na = A.length(), nb = B.length();
		String[][] dp = new String[na + 1][nb + 1];
		dp[0][0] = "";
		for (int i = 0; i <= na; i++)
			for (int j = 0; j <= nb; j++)
				if (dp[i][j] != null && i + j < ns) {
					if (i < na && (s[i + j] == '?' || s[i + j] == a[i])) {
						String next = dp[i][j] + a[i];
						if (dp[i + 1][j] == null
								|| dp[i + 1][j].compareTo(next) > 0)
							dp[i + 1][j] = next;
					}
					if (j < nb && (s[i + j] == '?' || s[i + j] == b[j])) {
						String next = dp[i][j] + b[j];
						if (dp[i][j + 1] == null
								|| dp[i][j + 1].compareTo(next) > 0)
							dp[i][j + 1] = next;
					}
				}
		if (dp[na][nb] == null)
			return "";
		return dp[na][nb];
	}

}
