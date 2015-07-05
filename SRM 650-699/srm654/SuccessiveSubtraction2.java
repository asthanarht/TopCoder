public class SuccessiveSubtraction2 {

	public int[] calc(int[] a, int[] p, int[] v) {
		int[] res = new int[p.length];
		for (int i = 0; i < p.length; i++) {
			a[p[i]] = v[i];
			res[i] = go(a);
		}
		return res;
	}

	private int go(int[] a) {
		int n = a.length;

		int sum = a[0];
		for (int i = 1; i < n; i++)
			sum -= a[i];

		int[] presum = new int[n];
		int[] premin = new int[n];
		int[] premax = new int[n];
		for (int i = 2; i < n; i++) {
			presum[i] = presum[i - 1] + a[i];
			premin[i] = Math.min(premin[i - 1], presum[i]);
			premax[i] = Math.max(premax[i - 1], presum[i] - premin[i]);
		}

		int[] sufsum = new int[n + 1];
		int[] sufmin = new int[n + 1];
		int[] sufmax = new int[n + 1];
		for (int i = n - 1; i >= 2; i--) {
			sufsum[i] = sufsum[i + 1] + a[i];
			sufmin[i] = Math.min(sufmin[i + 1], sufsum[i]);
			sufmax[i] = Math.max(sufmax[i + 1], sufsum[i] - sufmin[i]);
		}

		int max = 0;
		for (int i = 2; i < n; i++) {
			max = Math.max(max, premax[i]);
			if (i + 2 < n)
				max = Math.max(max, premax[i] + sufmax[i + 2]);
			max = Math.max(max, sufmax[i]);
		}

		return max + max + sum;
	}

}
