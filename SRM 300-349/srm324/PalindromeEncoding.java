package srm324;
public class PalindromeEncoding {
	public int getLength(String s) {
		int start, end;
		for (start = 0; start < s.length() - 1 && s.charAt(start) == s.charAt(start + 1); start++)
			;
		for (end = start; end < s.length() - 1 && s.charAt(end) != s.charAt(end + 1); end++)
			;
		return end - start + 1;
	}
}
