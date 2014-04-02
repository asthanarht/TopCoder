package srm614;

import java.util.Arrays;

public class MinimumSquareEasy {

	public long minArea(int[] x, int[] y) {
		int n = x.length;
		int K = n - 2;
		long minside = 1L << 34;
		for (int i = 0; i < n; i++)
			for (int j = i; j < n; j++) {
				// j==i place lower left corner at point[i]
				// j>i incorporate both point[i] and point[j]
				long minx = Math.min(x[i], x[j]) - 1;
				long miny = Math.min(y[i], y[j]) - 1;
				long[] side = new long[n];
				int index = 0;
				for (int k = 0; k < n; k++)
					if (x[k] > minx && y[k] > miny)
						side[index++] = Math.max(x[k] - minx, y[k] - miny) + 1;
				if (index < K)
					continue;
				Arrays.sort(side, 0, index);
				minside = Math.min(minside, side[K - 1]);
			}
		return minside * minside;
	}

}
