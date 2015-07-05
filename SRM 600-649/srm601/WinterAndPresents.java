public class WinterAndPresents {
	public long getNumber(int[] apple, int[] orange) {
		int n = apple.length;
		long res = 0;
		loop: for (int x = 1; x <= 2000000; x++) {
			int min = 0, max = 0;
			for (int i = 0; i < n; i++)
				if (apple[i] + orange[i] < x)
					break loop;
				else {
					if (x > orange[i])
						min += x - orange[i];
					max += Math.min(apple[i], x);
				}
			res += max - min + 1;
		}
		return res;
	}
}
