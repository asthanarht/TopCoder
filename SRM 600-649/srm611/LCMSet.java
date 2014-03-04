package srm611;

public class LCMSet {
	public String equal(int[] A, int[] B) {
		return (check(A, B) && check(B, A)) ? "Equal" : "Not equal";
	}

	private boolean check(int[] A, int[] B) {
		for (int i = 0; i < A.length; i++) {
			long lcm = 1;
			for (int j = 0; j < B.length; j++)
				if (A[i] % B[j] == 0)
					lcm = lcm(lcm, B[j]);
			if (A[i] != lcm)
				return false;
		}
		return true;
	}

	private long lcm(long a, long b) {
		return a / gcd(a, b) * b;
	}

	private long gcd(long a, long b) {
		while (b != 0) {
			long c = b;
			b = a % b;
			a = c;
		}
		return a;
	}
}
