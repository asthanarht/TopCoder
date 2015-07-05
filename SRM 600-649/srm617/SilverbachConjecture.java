public class SilverbachConjecture {

	public int[] solve(int n) {
		for (int num1 = 2; num1 < n; num1++) {
			int num2 = n - num1;
			boolean ok1 = false, ok2 = false;
			for (int num = 2; num < num1; num++)
				if (num1 % num == 0) {
					ok1 = true;
					break;
				}
			for (int num = 2; num < num2; num++)
				if (num2 % num == 0) {
					ok2 = true;
					break;
				}
			if (ok1 && ok2)
				return new int[] { num1, num2 };
		}
		return null;
	}

}
