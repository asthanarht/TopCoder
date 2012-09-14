package srm329;

import java.util.Arrays;

public class LogCutter {
	public String bestCut(int L, int A, int K, int C) {
		int[] pos = new int[K + 1];
		pos[0] = 0;
		for (int i = 1; i <= K; i++) {
			long p = A;
			p *= i;
			p %= (L - 1);
			pos[i] = (int) (p + 1);
		}
		Arrays.sort(pos);

		// binary search max length of all pieces
		String res = "";
		int left = 0;
		int right = L;
		while (left < right - 1) {
			int m = (left + right) / 2;
			boolean ok = true;
			int c = 0, end = L, max = m, first = L;
			for (int i = pos.length - 1; i >= 0; i--)
				if (i == 0 || end - pos[i - 1] > m)
					if (end - pos[i] <= m) {
						if (i != 0) {
							c++;
							first = pos[i];
						}
						end = pos[i];
						max = Math.max(max, end - pos[i]);
					} else {
						ok = false;
						break;
					}
			if (ok && c <= C) {
				right = max;
				res = max + " ";
				if (c < C)
					res += pos[1];
				else
					res += first;
			} else
				left = m;
		}

		return res;
	}
}
