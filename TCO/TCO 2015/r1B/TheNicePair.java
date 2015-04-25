package r1B;

public class TheNicePair {

	public int solve(int[] A) {
		int n = A.length;
		int k = -1;
		for (int i = 0; i < n; i++)
			for (int j = i; j < n; j++) {
				int total = j - i + 1;
				for (int d = 2; d <= 1000; d++) {
					int cnt = 0;
					for (int ii = i; ii <= j; ii++)
						if (A[ii] % d == 0)
							cnt++;
					if (cnt * 2 >= total)
						k = Math.max(k, j - i);
				}

			}
		return k;
	}

}
