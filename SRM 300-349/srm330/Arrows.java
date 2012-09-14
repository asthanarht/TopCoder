package srm330;

public class Arrows {
	public int longestArrow(String s) {
		int len = -1;
		for (int i = 0; i < s.length(); i++)
			if (s.charAt(i) == '<') {
				int count = 1;
				if (i + 1 < s.length()
						&& (s.charAt(i + 1) == '-' || s.charAt(i + 1) == '=')) {
					char sign = s.charAt(i + 1);
					for (int j = i + 1; j < s.length(); j++)
						if (s.charAt(j) == sign)
							count++;
						else
							break;
				}
				len = Math.max(len, count);
			} else if (s.charAt(i) == '>') {
				int count = 1;
				if (i - 1 >= 0
						&& (s.charAt(i - 1) == '-' || s.charAt(i - 1) == '=')) {
					char sign = s.charAt(i - 1);
					for (int j = i - 1; j >= 0; j--)
						if (s.charAt(j) == sign)
							count++;
						else
							break;
				}
				len = Math.max(len, count);
			}
		return len;
	}
}
