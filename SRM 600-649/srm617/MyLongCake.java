package srm617;

public class MyLongCake {

	public int cut(int n) {
		boolean[] cut = new boolean[n + 1];
		int ans = 0;
		for (int i = 1; i < n; i++)
			if (n % i == 0) {
				int diff = n / i;
				for (int len = diff; len <= n; len += diff)
					if (!cut[len]) {
						cut[len] = true;
						ans++;
					}
			}
		return ans;
	}

}
