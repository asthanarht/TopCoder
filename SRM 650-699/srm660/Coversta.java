package srm660;

public class Coversta {

	public int place(String[] a, int[] x, int[] y) {
		int n = a.length, m = a[0].length();
		int[][] value = new int[n][m];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				value[i][j] = a[i].charAt(j) - '0';
		int K = x.length;
		int[][] sum = new int[n][m];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				for (int k = 0; k < K; k++) {
					int r = i + x[k];
					int c = j + y[k];
					if (r >= 0 && r < n && c >= 0 && c < m)
						sum[i][j] += value[r][c];
				}
		int max = 0;
		for (int i1 = 0; i1 < n; i1++)
			for (int j1 = 0; j1 < m; j1++) {
				int[][] dup = new int[n][m];
				for (int k1 = 0; k1 < K; k1++) {
					int r1 = i1 + x[k1];
					int c1 = j1 + y[k1];
					if (r1 >= 0 && r1 < n && c1 >= 0 && c1 < m)
						for (int k2 = 0; k2 < K; k2++) {
							int r2 = r1 - x[k2];
							int c2 = c1 - y[k2];
							if (r2 >= 0 && r2 < n && c2 >= 0 && c2 < m)
								dup[r2][c2] += value[r1][c1];
						}
				}
				for (int i2 = i1; i2 < n; i2++)
					for (int j2 = 0; j2 < m; j2++)
						if (i2 != i1 || j2 != j1) {
							int cnt = sum[i1][j1] + sum[i2][j2] - dup[i2][j2];
							max = Math.max(max, cnt);
						}
			}
		return max;
	}

}
