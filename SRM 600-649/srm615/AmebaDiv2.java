public class AmebaDiv2 {

	public int simulate(int[] X, int A) {
		for (int i = 0; i < X.length; i++)
			if (A == X[i])
				A += X[i];
		return A;
	}

}
