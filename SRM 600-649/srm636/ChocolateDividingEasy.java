public class ChocolateDividingEasy {

	public int findBest(String[] chocolate) {
		int n = chocolate.length, m = chocolate[0].length();

		int[][] qual = new int[n][m];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				qual[i][j] = chocolate[i].charAt(j) - '0';

		int[][] top = new int[n][m];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++) {
				if (i == 0)
					top[0][j] = qual[0][j];
				else
					top[i][j] = top[i - 1][j] + qual[i][j];
			}

		int[][] topLeft = new int[n][m];
		for (int j = 0; j < m; j++)
			for (int i = 0; i < n; i++) {
				if (j == 0)
					topLeft[i][0] = top[i][0];
				else
					topLeft[i][j] = topLeft[i][j - 1] + top[i][j];
			}

		int max = 0;
		for (int i1 = 1; i1 < n; i1++)
			for (int i2 = i1 + 1; i2 < n; i2++)
				for (int j1 = 1; j1 < m; j1++)
					for (int j2 = j1 + 1; j2 < m; j2++) {
						int[][] sum = new int[3][3];
						sum[0][0] = topLeft[i1 - 1][j1 - 1];
						sum[0][1] = topLeft[i1 - 1][j2 - 1] - sum[0][0];
						sum[0][2] = topLeft[i1 - 1][m - 1]
								- topLeft[i1 - 1][j2 - 1];

						sum[1][0] = topLeft[i2 - 1][j1 - 1] - sum[0][0];
						sum[1][1] = topLeft[i2 - 1][j2 - 1]
								- topLeft[i2 - 1][j1 - 1] - sum[0][1];
						sum[1][2] = topLeft[i2 - 1][m - 1]
								- topLeft[i2 - 1][j2 - 1] - sum[0][2];

						sum[2][0] = topLeft[n - 1][j1 - 1]
								- topLeft[i2 - 1][j1 - 1];
						sum[2][1] = topLeft[n - 1][j2 - 1]
								- topLeft[n - 1][j1 - 1] - sum[0][1]
								- sum[1][1];
						sum[2][2] = topLeft[n - 1][m - 1]
								- topLeft[n - 1][j2 - 1] - sum[0][2]
								- sum[1][2];

						int min = sum[0][0];
						for (int i = 0; i < 3; i++)
							for (int j = 0; j < 3; j++)
								min = Math.min(min, sum[i][j]);
						max = Math.max(max, min);
					}
		return max;
	}

}
