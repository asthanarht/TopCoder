public class PairGame {

	public int maxSum(int a, int b, int c, int d) {
		while (true) {
			if (a == 0 || b == 0 || c == 0 || d == 0)
				return -1;
			if (a == c && b == d) {
				if (a == 1 && b == 1)
					return 2;
				return a + b;
			}
			if (Math.max(a, b) > Math.max(c, d)) {
				if (a > b)
					a -= b;
				else
					b -= a;
			} else {
				if (c > d)
					c -= d;
				else
					d -= c;
			}
		}
	}
}
