package srm618;
public class WritingWords {

	public int write(String word) {
		int cnt = 0;
		for (int i = 0; i < word.length(); i++)
			cnt += word.charAt(i) - 'A';
		return cnt + word.length();
	}

}
