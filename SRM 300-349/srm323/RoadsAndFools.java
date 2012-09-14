package srm323;

public class RoadsAndFools {
	public String determineOrientation(int length, int[] frontSides) {
		int n = frontSides.length;
		int[][] dp = new int[n][2];
		dp[0][0] = 1;
		dp[0][1] = 1;
		int[] back = new int[n];
		for (int i = 0; i < n; i++)
			back[i] = length - frontSides[i];
		for (int i = 1; i < n; i++) {
			if (frontSides[i] > frontSides[i - 1])
				dp[i][0] += dp[i - 1][0];
			if (back[i] > frontSides[i - 1])
				dp[i][1] += dp[i - 1][0];
			if (back[i - 1] != frontSides[i - 1]) {
				if (frontSides[i] > back[i - 1])
					dp[i][0] += dp[i - 1][1];
				if (back[i] > back[i - 1])
					dp[i][1] += dp[i - 1][1];
			}
		}
		int total = dp[n - 1][0] + dp[n - 1][1];
		if (frontSides[n - 1] == back[n - 1])
			total /= 2;
		if (total == 0)
			return "NO SOLUTION";
		if (total > 1)
			return "MULTIPLE SOLUTIONS";
		String result = "";
		int pre = -1;
		for (int i = 0; i < n; i++) {
			int min = Math.min(frontSides[i], back[i]);
			int max = Math.max(frontSides[i], back[i]);
			pre = min > pre ? min : max;
			result += " " + pre;
		}
		return result.trim();
	}
}
