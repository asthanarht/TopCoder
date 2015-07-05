public class FizzBuzzTurbo {

	public long[] counts(long A, long B) {
		long z = count(A, B, 15);
		long x = count(A, B, 3) - z;
		long y = count(A, B, 5) - z;
		return new long[] { x, y, z };
	}

	private long count(long A, long B, long div) {
		long first = A + (A % div == 0 ? 0 : div - A % div);
		if (first > B)
			return 0;
		return 1 + (B - first) / div;
	}

}
