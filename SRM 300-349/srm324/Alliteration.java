package srm324;
public class Alliteration {
	public int count(String[] words) {
		int count = 0;
		boolean ok = true;
		for (int i = 0; i < words.length - 1; i++) {
			char c = Character.toLowerCase(words[i].charAt(0));
			char cc = Character.toLowerCase(words[i + 1].charAt(0));
			if (c == cc && ok) {
				count++;
				ok = false;
			}
			if (c != cc)
				ok = true;
		}
		return count;
	}
}
