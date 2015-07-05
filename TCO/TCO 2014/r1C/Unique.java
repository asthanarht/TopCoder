public class Unique {

	public String removeDuplicates(String S) {
		boolean[] check = new boolean[26];
		String res = "";
		for (int i = 0; i < S.length(); i++)
			if (!check[S.charAt(i) - 'a']) {
				res += S.charAt(i);
				check[S.charAt(i) - 'a'] = true;
			}
		return res;
	}

}
