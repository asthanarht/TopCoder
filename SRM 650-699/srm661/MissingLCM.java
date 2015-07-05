public class MissingLCM {

	public int getMin(int N) {
		if (N <= 5)
			return N + N;
		int res = 2;
		int maxd = 500000;
		int[] divisors = new int[maxd];
		for (int M = 2; M <= N; M++) {
			boolean ok = true;
			int num = M;
			for (int d = 2; d * d <= num; d++)
				if (divisors[d] > 0 && num % d == 0) {
					int cnt = 0;
					while (num % d == 0) {
						cnt++;
						num /= d;
					}
					if (divisors[d] < cnt) {
						ok = false;
						divisors[d] = cnt;
					}
				}
			if ((num > 1 && num < maxd && divisors[num] == 0) || num > maxd) {
				ok = false;
				if (num < maxd)
					divisors[num] = 1;
			}
			if (!ok)
				res = M + M;
		}
		return res;
	}

}