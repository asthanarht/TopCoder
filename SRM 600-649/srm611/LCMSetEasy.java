public class LCMSetEasy {
	public String include(int[] S, int x) {
		long lcm = 1;
		for (int i = 0; i < S.length; i++)
			if (x % S[i] == 0)
				lcm = lcm(lcm, S[i]);
		if (lcm == x)
			return "Possible";
		return "Impossible";
	}

	private long lcm(long a, long b) {
		return a / gcd(a, b) * b;
	}

	private long gcd(long a, long b) {
		while (b != 0) {
			long c = a % b;
			a = b;
			b = c;
		}
		return a;
	}
}
