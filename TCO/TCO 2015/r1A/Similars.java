public class Similars {

	public int maxsim(int L, int R) {
		int[] sig = new int[1 << 10];
		for (int i = R; i >= L; i--) {
			int s = 0;
			int n = i;
			while (n > 0) {
				s |= 1 << (n % 10);
				n /= 10;
			}
			sig[s]++;
		}
		int max = 0;
		for (int i = 1; i < (1 << 10); i++) {
			int n = i;
			int cnt = 0;
			while (n > 0) {
				cnt += n & 1;
				n >>= 1;
			}
			if (cnt > max) {
				int total = 0;
				for (int j = 1; j < (1 << 10); j++)
					if ((i & j) == i)
						total += sig[j];
				if (total >= 2)
					max = Math.max(max, cnt);
			}
		}
		return max;
	}

}
