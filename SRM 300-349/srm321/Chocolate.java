package srm321;

public class Chocolate {
	public int minSplitNumber(int width, int height, int nTiles) {
		long w = width, h = height, n = nTiles;
		if (w * h < n)
			return -1;
		if (n == w * h)
			return 0;
		if (n % w == 0 || n % h == 0)
			return 1;
		for (int i = 1; i <= Math.sqrt(n); i++)
			if (n % i == 0) {
				long j = n / i;
				if ((i < w && j < h) || (i < h && j < w))
					return 2;
			}
		return -1;
	}
}
