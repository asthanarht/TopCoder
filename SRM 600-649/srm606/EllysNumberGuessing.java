package srm606;

public class EllysNumberGuessing {
	public int getNumber(int[] guesses, int[] answers) {
		int n = guesses.length;
		int[] num = { guesses[0] + answers[0], guesses[0] - answers[0] };
		boolean[] ok = { true, true };
		if (num[0] < 1 || num[0] > 1e9)
			ok[0] = false;
		if (num[1] < 1 || num[1] > 1e9)
			ok[1] = false;
		for (int i = 1; i < n; i++) {
			int[] guess = { guesses[i] + answers[i], guesses[i] - answers[i] };
			if (guess[0] != num[0] && guess[1] != num[0])
				ok[0] = false;
			if (guess[0] != num[1] && guess[1] != num[1])
				ok[1] = false;
		}
		if (ok[0] && ok[1])
			return -1;
		if (!ok[0] && !ok[1])
			return -2;
		return ok[0] ? num[0] : num[1];
	}
}
