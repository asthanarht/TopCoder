public class ColorfulLineGraphs {

	public int countWays(long N, long K, int M) {
		long p = 1;
		for (int i = 0; i < M; i++) {
			long mul = (i * (K - 1) + K) % M;
			p = (p * mul) % M;
		}
		long res = pow(p, N / M, M);
		for (int i = 0; i < N % M; i++) {
			long mul = (i * (K - 1) + K) % M;
			res = (res * mul) % M;
		}
		return (int) res;
	}

	private long pow(long base, long rise, long mod) {
		long res = 1;
		while (rise > 0) {
			if ((rise & 1) > 0)
				res = (res * base) % mod;
			base = (base * base) % mod;
			rise >>= 1;
		}
		return res;
	}

}
