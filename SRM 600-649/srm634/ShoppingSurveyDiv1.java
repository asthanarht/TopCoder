package srm634;

public class ShoppingSurveyDiv1 {

	public int minValue(int N, int K, int[] s) {
		for (int i = 0; i < N; i++)
			if (test(N, K, s, i))
				return i;
		return N;
	}

	private boolean test(int N, int K, int[] s, int c) {
		int[] copy = (int[]) s.clone();
		int sum = 0;
		for (int i = 0; i < copy.length; i++) {
			copy[i] = Math.max(0, copy[i] - c);
			sum += copy[i];
		}
		if (sum <= (N - c) * (K - 1))
			return true;
		return false;
	}
}
