import java.util.Arrays;

public class MysticAndCandies {

	public int minBoxes(int C, int X, int[] low, int[] high) {
		int n = low.length;
		Arrays.sort(low);
		int sum = 0, res = n;
		for (int i = n - 1; i >= 0; i--) {
			sum += low[i];
			if (sum >= X)
				res = Math.min(res, n - i);
		}
		Arrays.sort(high);
		sum = 0;
		for (int i = 0; i < n; i++) {
			sum += high[i];
			if (C - sum >= X)
				res = Math.min(res, n - 1 - i);
		}
		return res;
	}

}
