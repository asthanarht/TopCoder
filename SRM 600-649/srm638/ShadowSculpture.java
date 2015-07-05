public class ShadowSculpture {

	public String possible(String[] XY, String[] YZ, String[] ZX) {
		int n = XY.length;
		int[][][] cube = new int[n][n][n];
		remove(XY, YZ, ZX, cube, n);
		int count = 2;
		boolean blank = true;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				for (int k = 0; k < n; k++)
					if (cube[i][j][k] == 1) {
						blank = false;
						if (test(XY, YZ, ZX, cube, n, i, j, k, count))
							return "Possible";
						count++;
					}
		if (blank) {
			boolean found = false;
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					if (XY[i].charAt(j) == 'Y')
						found = true;
			for (int j = 0; j < n; j++)
				for (int k = 0; k < n; k++)
					if (YZ[j].charAt(k) == 'Y')
						found = true;
			for (int k = 0; k < n; k++)
				for (int i = 0; i < n; i++)
					if (ZX[k].charAt(i) == 'Y')
						found = true;
			if (found)
				return "Impossible";
			return "Possible";
		}
		return "Impossible";
	}

	private boolean test(String[] XY, String[] YZ, String[] ZX, int[][][] cube,
			int n, int I, int J, int K, int key) {
		cube[I][J][K] = key;
		while (true) {
			boolean found = false;
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					for (int k = 0; k < n; k++)
						if (cube[i][j][k] == key) {
							if (i > 0 && cube[i - 1][j][k] == 1) {
								cube[i - 1][j][k] = key;
								found = true;
							}
							if (i + 1 < n && cube[i + 1][j][k] == 1) {
								cube[i + 1][j][k] = key;
								found = true;
							}
							if (j > 0 && cube[i][j - 1][k] == 1) {
								cube[i][j - 1][k] = key;
								found = true;
							}
							if (j + 1 < n && cube[i][j + 1][k] == 1) {
								cube[i][j + 1][k] = key;
								found = true;
							}
							if (k > 0 && cube[i][j][k - 1] == 1) {
								cube[i][j][k - 1] = key;
								found = true;
							}
							if (k + 1 < n && cube[i][j][k + 1] == 1) {
								cube[i][j][k + 1] = key;
								found = true;
							}
						}
			if (!found)
				break;
		}

		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (XY[i].charAt(j) == 'Y') {
					boolean ok = false;
					for (int k = 0; k < n; k++)
						if (cube[i][j][k] == key)
							ok = true;
					if (!ok)
						return false;
				}

		for (int j = 0; j < n; j++)
			for (int k = 0; k < n; k++)
				if (YZ[j].charAt(k) == 'Y') {
					boolean ok = false;
					for (int i = 0; i < n; i++)
						if (cube[i][j][k] == key)
							ok = true;
					if (!ok)
						return false;
				}
		for (int k = 0; k < n; k++)
			for (int i = 0; i < n; i++)
				if (ZX[k].charAt(i) == 'Y') {
					boolean ok = false;
					for (int j = 0; j < n; j++)
						if (cube[i][j][k] == key)
							ok = true;
					if (!ok)
						return false;
				}

		return true;
	}

	private void remove(String[] XY, String[] YZ, String[] ZX, int[][][] cube,
			int n) {
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				for (int k = 0; k < n; k++)
					cube[i][j][k] = 1;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (XY[i].charAt(j) == 'N')
					for (int k = 0; k < n; k++)
						cube[i][j][k] = 0;
		for (int j = 0; j < n; j++)
			for (int k = 0; k < n; k++)
				if (YZ[j].charAt(k) == 'N')
					for (int i = 0; i < n; i++)
						cube[i][j][k] = 0;
		for (int k = 0; k < n; k++)
			for (int i = 0; i < n; i++)
				if (ZX[k].charAt(i) == 'N')
					for (int j = 0; j < n; j++)
						cube[i][j][k] = 0;
	}

}
