public class ProblemSets {

	public long maxSets(long E, long EM, long M, long MH, long H) {
		long left = 0, right = Long.MAX_VALUE;
		while (left + 1 < right) {
			long mid = left + (right - left) / 2;
			if (valid(E, EM, M, MH, H, mid))
				left = mid;
			else
				right = mid;
		}
		return left;
	}

	private boolean valid(long E, long EM, long M, long MH, long H, long X) {
		long EM2E = X > E ? Math.min(EM, X - E) : 0;
		EM -= EM2E;
		E += EM2E;
		long EM2M = X > M ? Math.min(EM, X - M) : 0;
		EM -= EM2M;
		M += EM2M;
		long MH2M = X > M ? Math.min(MH, X - M) : 0;
		MH -= MH2M;
		M += MH2M;
		long MH2H = X > H ? Math.min(MH, X - H) : 0;
		MH -= MH2H;
		H += MH2H;
		return E >= X && M >= X && H >= X;
	}

}
