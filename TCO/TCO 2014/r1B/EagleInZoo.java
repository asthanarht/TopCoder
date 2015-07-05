import java.util.ArrayList;

public class EagleInZoo {

	public double calc(int[] parent, int K) {
		double[][] comb = new double[K][K];
		for (int i = 0; i < K; i++) {
			comb[i][0] = 1;
			for (int j = 1; j <= i; j++)
				comb[i][j] = comb[i - 1][j - 1] + comb[i - 1][j];
		}
		int N = parent.length + 1;
		@SuppressWarnings("unchecked")
		ArrayList<Integer>[] children = new ArrayList[N];
		for (int p = 0; p < N; p++)
			children[p] = new ArrayList<Integer>();
		for (int c = 1; c < N; c++)
			children[parent[c - 1]].add(c);

		// prob[v][k] = probability that k of (K - 1) eagle go to vertex v
		double[][] dp = new double[N][K];
		dp[0][K - 1] = 1;
		ArrayList<Integer> level = new ArrayList<Integer>();
		level.add(0);
		double res = 0;
		while (level.size() > 0) {
			ArrayList<Integer> next = new ArrayList<Integer>();
			for (Integer p : level) {
				// probability that the current vertex is empty
				res += dp[p][0];
				if (children[p].size() == 0)
					continue;
				// np eagle at p go to p's children
				for (int np = 0; np + 1 < K; np++)
					if (dp[p][np + 1] > 0) {
						int size = children[p].size();
						// nc eagle go to one of p's children
						for (int nc = 0; nc <= np; nc++) {
							// pick this child
							double p1 = 1.0 / size;
							// pick nc from np
							double p2 = comb[np][nc];
							// nc go to this child
							double p3 = Math.pow(1.0 / size, nc);
							// the rest go to other children
							double p4 = Math.pow((size - 1.0) / size, np - nc);
							// equal rights
							for (Integer c : children[p])
								dp[c][nc] += dp[p][np + 1] * p1 * p2 * p3 * p4;
						}
					}
				for (Integer c : children[p])
					next.add(c);
			}
			level = next;
		}
		return res;
	}

}
