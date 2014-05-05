package r1C;

public class RedPaint {

	public double expectedCells(int N) {
		double[][] dp1 = new double[N + 1][N + 1];
		dp1[0][0] = 1;
		for (int i = 0; i < N; i++) {
			double[][] dp2 = new double[N + 1][N + 1];
			for (int h = 0; h <= i; h++) {
				// move left
				// move left at 0 == stay 0 increase h
				dp2[h + 1][0] += 0.5 * dp1[h][0];
				for (int pos = 1; pos <= h; pos++)
					dp2[h][pos - 1] += 0.5 * dp1[h][pos];
				// move right
				for (int pos = 0; pos < h; pos++)
					dp2[h][pos + 1] += 0.5 * dp1[h][pos];
				dp2[h + 1][h + 1] += 0.5 * dp1[h][h]; // pos == h
			}
			dp1 = dp2;
		}
		double res = 0;
		for (int h = 0; h <= N; h++)
			for (int pos = 0; pos <= h; pos++)
				res += dp1[h][pos] * (h + 1);
		return res;
	}

}
