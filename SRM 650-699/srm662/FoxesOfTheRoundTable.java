import java.util.Arrays;

public class FoxesOfTheRoundTable {

	public int[] minimalDifference(int[] h) {
		int n = h.length;
		int[] copy = new int[n];
		for (int i = 0; i < n; i++)
			copy[i] = h[i];
		Arrays.sort(copy);
		int max = copy[n - 1] - copy[0];
		int min = 0;
		while (max > min) {
			int mid = (max + min) / 2;
			if (test(copy, mid))
				max = mid;
			else
				min = mid + 1;
		}
		System.out.println(max);
		return res(h, per(copy, max));
	}

	private int[] res(int[] h, int[] per) {
		int n = h.length;
		boolean[] used = new boolean[n];
		int[] ans = new int[n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				if (!used[j] && h[j] == per[i]) {
					ans[i] = j;
					used[j] = true;
					break;
				}
		}
		return ans;
	}

	private boolean test(int[] h, int d) {
		int n = h.length;
		int[] p = per(h, d);
		for (int i = 0; i < n; i++)
			if (Math.abs(p[i] - p[(i + 1) % n]) > d)
				return false;
		return true;
	}

	private int[] per(int[] h, int d) {
		int n = h.length;
		int[] p = new int[n];
		boolean[] used = new boolean[n];
		p[0] = h[0];
		used[0] = true;
		int pi = 0;
		for (int i = 1; i < n; i++)
			if (i + 1 == n || Math.abs(p[pi] - h[i + 1]) > d) {
				pi++;
				p[pi] = h[i];
				used[i] = true;
			}
		for (int i = n - 1; i > 0; i--)
			if (!used[i])
				p[++pi] = h[i];
		return p;
	}

}
