import java.util.Arrays;

public class PackingBallsDiv1 {

	public int minPacks(int K, int A, int B, int C, int D) {
		long[] X = new long[K];
		X[0] = A;
		for (int i = 1; i < K; i++)
			X[i] = (X[i - 1] * B + C) % D + 1;
		int res = 0;
		for (int i = 0; i < K; i++) {
			res += X[i] / K;
			X[i] %= K;
			if (X[i] == 0) {
				res--;
				X[i] = K;
			}
		}

		Arrays.sort(X);
		int min = (int) X[K - 1];
		if (min != 0) {
			long pre = 0;
			for (int i = 0; i < K; i++)
				if (X[i] > pre) {
					min = (int) Math.min(min, pre + K - i);
					pre = X[i];
				}
		}

		return res + min;
	}

}
