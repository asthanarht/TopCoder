public class ApplesAndOrangesEasy {

	public int maximumApples(int N, int K, int[] info) {
		int[] apple = new int[N + 1];
		for (Integer a : info)
			apple[a] = 1;

		int max = K / 2;

		int res = 0;
		for (int i = 1; i <= N; i++) {
			if (apple[i] == 0) {
				boolean ok = true;
				int lb = Math.max(1, i - K + 1);
				int ub = Math.min(N, i + K - 1);
				int cnt = 0;
				for (int j = lb; j <= ub; j++) {
					if (j - K >= lb)
						cnt -= apple[j - K];
					cnt += apple[j];
					if (cnt >= max)
						ok = false;
				}
				if (ok)
					apple[i] = 1;
			}
			res += apple[i];
		}

		return res;
	}

}
