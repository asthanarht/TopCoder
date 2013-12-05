package srm599;

public class FindPolygons {
	long p;

	public double minimumPolygon(int L) {
		if (L % 2 == 1 || L < 4)
			return -1;
		p = L / 2;
		int diff = L;
		for (int a = 1; a < L / 2; a++)
			for (int b = 1; b <= a; b++) {
				int c = L - a - b;
				if (check(a, b, c)) {
					int max = Math.max(a, Math.max(b, c));
					int min = Math.min(a, Math.min(b, c));
					diff = Math.min(diff, max - min);
				}
			}
		if (diff != L)
			return diff;
		return L % 4 == 0 ? 0 : 1;
	}

	public boolean check(int a, int b, int c) {
		if (a + b <= c || b + c <= a || a + c <= b)
			return false;
		if (Math.abs(a - b) >= c || Math.abs(a - c) >= b
				|| Math.abs(b - c) >= a)
			return false;
		long s2 = p * (p - a) * (p - b) * (p - c);
		long s = (long) Math.sqrt(s2);
		return s * s == s2;
	}
}
