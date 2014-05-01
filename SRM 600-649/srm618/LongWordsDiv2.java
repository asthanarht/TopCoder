package srm618;

public class LongWordsDiv2 {

	public String find(String word) {
		for (int i = 1; i < word.length(); i++)
			if (word.charAt(i - 1) == word.charAt(i))
				return "Dislikes";
		for (int ch1 = 'A'; ch1 <= 'Z'; ch1++) {
			int cnt = 0;
			for (int i = 0; i < word.length(); i++)
				if (word.charAt(i) == ch1)
					cnt++;
			if (cnt >= 4)
				return "Dislikes";
			for (int ch2 = 'A'; ch2 <= 'Z'; ch2++)
				if (ch2 != ch1) {
					int index1 = word.indexOf(ch1);
					if (index1 == -1)
						continue;
					String seg = word.substring(index1 + 1);
					int index2 = seg.indexOf(ch2);
					if (index2 == -1)
						continue;
					seg = seg.substring(index2 + 1);
					index1 = seg.indexOf(ch1);
					if (index1 == -1)
						continue;
					seg = seg.substring(index1 + 1);
					index2 = seg.indexOf(ch2);
					if (index2 != -1)
						return "Dislikes";
				}
		}
		return "Likes";
	}

}
