public class ConvexPolygonGame {
	public String winner(int[] X, int[] Y) {
		int n = X.length;

		int topRight = 0, bottomLeft = 0;
		for (int i = 0; i < n; i++) {
			if (X[i] > X[topRight]
					|| (X[i] == X[topRight] && Y[i] > Y[topRight]))
				topRight = i;
			if (X[i] < X[bottomLeft]
					|| (X[i] == X[bottomLeft] && Y[i] < Y[bottomLeft]))
				bottomLeft = i;
		}

		int upperHullFrom = bottomLeft;
		if (X[(bottomLeft + n - 1) % n] == X[bottomLeft])
			upperHullFrom = (bottomLeft + n - 1) % n;
		int lowerHullFrom = bottomLeft;

		for (int x = X[bottomLeft]; x <= X[topRight]; x++) {
			if (X[(upperHullFrom + n - 1) % n] < x)
				upperHullFrom = (upperHullFrom + n - 1) % n;
			if (X[(lowerHullFrom + 1) % n] < x)
				lowerHullFrom = (lowerHullFrom + 1) % n;
			search(X, Y, upperHullFrom, lowerHullFrom, x);
			if (found)
				return "Masha";
		}
		return "Petya";
	}

	public void search(int[] X, int[] Y, int upperHullFrom, int lowerHullFrom,
			int x) {
		int n = X.length;

		int upperY = (int) intersect(X[upperHullFrom], Y[upperHullFrom],
				X[(upperHullFrom + n - 1) % n], Y[(upperHullFrom + n - 1) % n],
				x, false);
		if (X[upperHullFrom] == x || X[(upperHullFrom + n - 1) % n] == x)
			upperY--;

		int lowerY = (int) intersect(X[lowerHullFrom], Y[lowerHullFrom],
				X[(lowerHullFrom + 1) % n], Y[(lowerHullFrom + 1) % n], x, true);
		if (X[lowerHullFrom] == x || X[(lowerHullFrom + 1) % n] == x)
			lowerY++;

		if (lowerY <= upperY) {
			check(x, lowerY);
			check(x, upperY);
		}
	}

	public int intersect(int x1, int y1, int x2, int y2, long x, boolean above) {
		long a = y2 - y1;
		long b = x1 - x2;
		long c = -a * x1 - b * y1;
		int y = (int) ((-c - a * x) / b);
		if (!above && y > (double) (-c - a * x) / b)
			y--;
		if (above && y < (double) (-c - a * x) / b)
			y++;
		return y;
	}

	public int x1, y1, x2, y2;
	public int cnt = 0;
	public boolean found = false;

	public void check(int x, int y) {
		if (cnt == 0) {
			x1 = x;
			y1 = y;
			cnt++;
		} else if (cnt == 1) {
			if (x != x1 || y != y1) {
				x2 = x;
				y2 = y;
				cnt++;
			}
		} else {
			long a = y2 - y1;
			long b = x1 - x2;
			long c = -(a * x1 + b * y1);
			if (a * x + b * y + c != 0)
				found = true;
		}
	}
}
