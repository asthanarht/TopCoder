package srm542;

public class PatrolRoute {
	public static int countRoutes(int X, int Y, int minT, int maxT) {
		long res = 0, mod = 1000000007;
		for (int w = 2; w < Math.min(maxT, X); w++)
			for (int h = Math.max(((minT + 1) / 2) - w, 2); h <= Math.min(
					(maxT / 2) - w, Y - 1); h++) {
				long num = (w - 1) * (h - 1) * 6;
				num *= (X - w) * (Y - h);
				num %= mod;
				res += num;
				res %= mod;
			}
		return (int) res;
	}
}
