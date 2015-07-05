public class EllysLamps {

	public int getMin(String lamps) {
		int n = lamps.length(), res = 0;
		char[] lamp = lamps.toCharArray();
		for (int i = 0; i < n - 1; i++)
			if (lamp[i] != lamp[i + 1]) {
				res++;
				i++;
			} else if (lamp[i] == 'Y' && i + 2 < n && lamp[i + 2] == 'Y') {
				res++;
				i += 2;
			}
		return res;
	}

}
