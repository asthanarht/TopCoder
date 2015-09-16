public class NinjaTurtles {

	public int countOpponents(int P, int K) {
		int left = 1, right = 2000000000;
		while (left < right) {
			int mid = (left + right) / 2;
			int p = (mid / K) * 3 + mid / 3;
			if (p < P)
				left = mid + 1;
			else
				right = mid;
		}
		int p = (left / K) * 3 + left / 3;
		return p == P ? left : -1;
	}

}
