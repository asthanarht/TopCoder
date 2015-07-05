public class BigFatInteger {
	public int minOperations(int A, int B) {
		int res = 0, max = 0;
		// find out all prime factors
		for (int i = 2; i * i <= A; i++)
			if (A % i == 0) {
				res++; // first, make a number by multiply all prime factors
				int t = 0;
				while (A % i == 0) {
					A /= i;
					t++;
				}
				max = Math.max(max, t);
			}
		// if A=6=2*3, 3 will not be found by the above for loop
		if (A > 1) {
			res++;
			max = Math.max(max, 1);
		}
		// rise the prime factor with the biggest counter by max*B times
		// operation 2 ensures that other prime factors can be done
		max *= B;
		for (int i = 1; i < max; i <<= 1)
			res++;
		return res;
	}
}
