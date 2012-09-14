package srm322;

public class ExtendedDominoes {
	public long countCycles(String[] pieces) {
		int[] pairing = new int[10];
		pairing[0] = 1;
		pairing[2] = 1;
		for (int i = 4; i < 10; i += 2)
			pairing[i] = (i - 1) * pairing[i - 2];
		int[] degree = new int[10];
		for (int i = 0; i < pieces.length; i++) {
			degree[pieces[i].charAt(0) - '0']++;
			degree[pieces[i].charAt(1) - '0']++;
		}
		long result = 1;
		for (int i = 0; i < degree.length; i++)
			if (degree[i] % 2 == 1)
				return 0;
			else
				result *= pairing[degree[i]];
		return result;
	}
}
