public class TPS {
	public int n;
	boolean[][] tree;

	public int minimalBeacons(String[] linked) {
		n = linked.length;
		if (n == 1)
			return 0;
		tree = new boolean[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (linked[i].charAt(j) == 'Y')
					tree[i][j] = true;
		int res = n;
		for (int i = 0; i < n; i++)
			res = Math.min(res, 1 + dfs(i, -1));
		return res;
	}

	public int dfs(int root, int parent) {
		int sum = 0, cnt = 0;
		for (int i = 0; i < n; i++)
			if (tree[root][i] && i != parent) {
				int extra = dfs(i, root);
				if (extra == 0)
					cnt++;
				sum += extra;
			}
		if (cnt > 1)
			sum += cnt - 1;
		return sum;
	}
}
