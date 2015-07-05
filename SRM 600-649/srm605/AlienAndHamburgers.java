public class AlienAndHamburgers {
	public int getNumber(int[] type, int[] taste) {
		int n = type.length;
		int Y = 0, A = 0;
		boolean[] select = new boolean[n];
		int[] tastes = new int[101];
		for (int i = 0; i < n; i++)
			if (taste[i] > 0) {
				tastes[type[i]] += taste[i];
				select[i] = true;
			}
		for (int i = 0; i < n; i++)
			if (tastes[type[i]] > 0)
				select[i] = true;
		for (int i = 1; i <= 100; i++)
			if (tastes[i] > 0) {
				Y++;
				A += tastes[i];
			}
		while (true) {
			int best = -1000000;
			int besti = -1;
			boolean found = false;
			for (int i = 0; i < n; i++)
				if (!select[i]) {
					if (taste[i] > best) {
						best = taste[i];
						besti = i;
						found = true;
					}
				}
			if (found) {
				if ((Y + 1) * (A + best) >= Y * A) {
					Y++;
					A += best;
					for (int i = 0; i < n; i++)
						if (type[i] == type[besti])
							select[i] = true;
					continue;
				}
			}
			break;
		}
		return Y * A;
	}
}
