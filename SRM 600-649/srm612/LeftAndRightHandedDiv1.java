package srm612;

public class LeftAndRightHandedDiv1 {
	public long countSwaps(String Y, int A, int B, int C, int D, int N) {
		int[] Li = new int[N + N]; // index of 'L'
		int cntL = 0;
		long cur = A;
		for (int i = 0; i < N; i++) {
			char c = Y.charAt((int) (cur % Y.length()));
			if (c == 'L')
				Li[cntL++] = i;
			long next = (cur * B + C) % D;
			cur = next;
		}
		if (cntL < 2 || N - cntL < 2)
			return 0;
		for (int i = 0; i < cntL; i++)
			Li[i + cntL] = Li[i] + N;

		// moves needed to move 'L's (upto the ith 'L') from index 0 to place
		long[] sum = new long[cntL + cntL + 1];
		for (int i = 0; i < cntL + cntL; i++)
			sum[i + 1] = sum[i] + Li[i];

		long min = Long.MAX_VALUE;
		for (int first = 0; first < cntL; first++) {
			int last = first + cntL - 1;
			int mid = first + cntL / 2;
			long left = (long) (mid - first) * Li[mid];
			long left0 = sum[mid] - sum[first];
			long left1 = (long) (mid - first) * (mid - first + 1) / 2;
			long right = sum[last + 1] - sum[mid + 1];
			long right0 = (long) (last - mid) * Li[mid];
			long right1 = (long) (last - mid) * (last - mid + 1) / 2;
			long move = left - left0 - left1 + (right - right0 - right1);
			min = Math.min(min, move);
		}

		return min;
	}
}
