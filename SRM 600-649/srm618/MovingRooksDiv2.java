public class MovingRooksDiv2 {
	int n;
	boolean[] check = new boolean[100000000];

	public String move(int[] Y1, int[] Y2) {
		n = Y1.length;
		go(Y1);
		return check[toInt(Y2)] ? "Possible" : "Impossible";
	}

	private void go(int[] Y) {
		int num = toInt(Y);
		if (check[num])
			return;
		check[num] = true;
		for (int i = 0; i < n; i++)
			for (int j = i + 1; j < n; j++)
				if (Y[i] > Y[j]) {
					int temp = Y[i];
					Y[i] = Y[j];
					Y[j] = temp;
					go(Y);
					Y[j] = Y[i];
					Y[i] = temp;
				}
	}

	private int toInt(int[] Y) {
		int num = 0;
		for (int i = 0; i < n; i++) {
			num *= 10;
			num += Y[i];
		}
		return num;
	}

}
