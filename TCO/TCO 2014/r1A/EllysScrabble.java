package r1A;

public class EllysScrabble {

	public String getMin(String letters, int maxDistance) {
		int n = letters.length();
		char[] original = letters.toCharArray();
		boolean[] used = new boolean[n];
		char[] res = new char[n];
		for (int i = 0; i < n; i++) {
			if (i - maxDistance >= 0 && !used[i - maxDistance])
				res[i] = original[i - maxDistance];
			else {
				char min = 'Z' + 1;
				int minj = 0;
				for (int j = Math.max(0, i - maxDistance); j <= Math.min(n - 1,
						i + maxDistance); j++)
					if (!used[j] && original[j] < min) {
						min = original[j];
						minj = j;
					}
				res[i] = original[minj];
				used[minj] = true;
			}
		}
		String ans = String.valueOf(res);
		return ans;
	}

}
