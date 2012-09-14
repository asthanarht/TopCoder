package srm324;
public class PalindromeDecoding {
	public String decode(String code, int[] position, int[] length) {
		for (int i = 0; i < position.length; i++) {
			String start = code.substring(0, position[i] + length[i]);
			String end = code.substring(position[i] + length[i]);
			String seg = start.substring(position[i]);
			code = start;
			for (int j = seg.length() - 1; j >= 0; j--)
				code += seg.charAt(j);
			code += end;
		}
		return code;
	}
}
