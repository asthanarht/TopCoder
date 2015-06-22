package r2B;

public class Bitwisdom {

	public double expectedActions(int[] p) {
		int n = p.length;
		double[][] ps = new double[n][2];
		for (int i = 0; i < n; i++) {
			ps[i][1] = p[i] * 0.01;
			ps[i][0] = 1 - ps[i][1];
		}

		double[][][] dp = new double[n][n + 1][2];
		dp[0][1][0] = ps[0][0];
		dp[0][1][1] = ps[0][1];
		for (int i = 0; i + 1 < n; i++)
			for (int k = 1; k < n; k++)
				for (int d : new int[] { 0, 1 })
					if (dp[i][k][d] > 0) {
						dp[i + 1][k][d] += dp[i][k][d] * ps[i + 1][d];
						dp[i + 1][k + 1][1 - d] += dp[i][k][d]
								* ps[i + 1][1 - d];
					}

		double res = 0;
		for (int k = 1; k <= n; k++)
			for (int d : new int[] { 0, 1 }) {
				int move = 0;
				if (k == 1)
					move = d;
				else if (k == 2)
					move = 1;
				else if (k >= 3)
					move = k - 1;
				res += dp[n - 1][k][d] * move;
			}
		return res;
	}

}
