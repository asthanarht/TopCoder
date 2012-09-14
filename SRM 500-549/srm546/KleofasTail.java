package srm546;

public class KleofasTail {
	private long A;
	private long B;

	public long countGoodSequences(long K, long A, long B) {
		this.A = A;
		this.B = B;
		if (K <= 2) {
			if (B >= K)
				return B - Math.max(A, K) + 1;
			return 0;
		} else {
			if (K % 2 == 0)
				return dfs(K, K + 1);
			return dfs(K, K);
		}
	}

	private long dfs(long from, long to) {
		if (to < A)
			return dfs(from * 2, to * 2 + 1);
		else if (from > B)
			return 0;
		else if (to >= B)
			return B - Math.max(A, from) + 1;
		else
			return to - Math.max(A, from) + 1 + dfs(from * 2, to * 2 + 1);
	}
}
