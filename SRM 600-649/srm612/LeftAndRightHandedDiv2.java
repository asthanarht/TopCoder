public class LeftAndRightHandedDiv2 {
	public int count(String S) {
		int cnt = 0;
		for (int i = 0; i + 1 < S.length(); i++)
			if (S.charAt(i) == 'R' && S.charAt(i + 1) == 'L')
				cnt++;
		return cnt;
	}
}
