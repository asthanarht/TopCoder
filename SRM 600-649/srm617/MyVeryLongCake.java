import java.util.HashSet;

public class MyVeryLongCake {

	public int cut(int n) {
		return n - eulerTotient(n);
	}

	private int eulerTotient(int n) {
		for (Integer pd : primeDivisor(n))
			n -= n / pd;
		return n;
	}

	private HashSet<Integer> primeDivisor(int n) {
		HashSet<Integer> pd = new HashSet<Integer>();
		for (int p = 2; p * p <= n; p++)
			if (n % p == 0) {
				pd.add(p);
				while (n % p == 0)
					n /= p;
			}
		if (n > 1)
			pd.add(n);
		return pd;
	}

}
