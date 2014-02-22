package srm609;

public class MagicalStringDiv1 {

	public int getLongest(String S) {
		int n = S.length();
		int max = 0;
		for (int mid = 0; mid <= n; mid++) {
			int cntL = 0, cntR = 0;
			for (int i = 0; i < mid; i++)
				if (S.charAt(i) == '>')
					cntL++;
			for (int i = mid; i < n; i++)
				if (S.charAt(i) == '<')
					cntR++;
			max = Math.max(max, Math.min(cntL, cntR) * 2);
		}
		return max;
	}

}
