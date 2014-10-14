package srm441;

public class PerfectPermutation {

	public int reorder(int[] P) {
		int n = P.length;
		boolean[] check = new boolean[n];
		int count = 0;
		while (true) {
			boolean found = false;
			for (int i = 0; i < n; i++)
				if (!check[i]) {
					found = true;
					int index = i;
					do {
						check[index] = true;
						index = P[index];
					} while (index != i);
					break;
				}
			if (!found)
				break;
			else
				count++;
		}
		if (count == 1)
			return 0;
		return count;
	}

}
