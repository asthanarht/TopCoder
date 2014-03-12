package srm611;

public class InterestingNumber {
	public String isInteresting(String x) {
		for (int i = 0; i <= 9; i++)
			if (!check(x, i))
				return "Not interesting";
		return "Interesting";
	}

	private boolean check(String x, int d) {
		char c = String.valueOf(d).charAt(0);
		int cnt = 0, c1 = -1, c2 = -1;
		for (int i = 0; i < x.length(); i++)
			if (x.charAt(i) == c) {
				cnt++;
				if (c1 == -1)
					c1 = i;
				else if (c2 == -1)
					c2 = i;
			}
		if (cnt == 0 || (cnt == 2 && c2 - 1 - c1 == d))
			return true;
		return false;
	}
}
