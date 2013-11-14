package srm592;

import java.util.Arrays;

public class LittleElephantAndBalls {
	public char[] color = { 'R', 'G', 'B' };

	public int getNumber(String S) {
		int[] cnt = new int[3];
		return insert(S, 0, "", 0, cnt);
	}

	public int insert(String S, int index, String seq, int score, int[] cnt) {
		if (index == S.length())
			return score;
		int max = 0;
		int ci = getColorID(S.charAt(index));
		if (cnt[ci] >= 2) {
			for (int loc = 0; loc <= seq.length(); loc++)
				max = Math.max(max, getScore(seq, loc));
			max += score;
			max = insert(S, index + 1, seq, max, cnt.clone());
		} else {
			for (int loc = 0; loc <= seq.length(); loc++) {
				String newSeq = seq.substring(0, loc) + color[ci]
						+ seq.substring(loc);
				int[] newCnt = cnt.clone();
				newCnt[ci]++;
				int newScore = score + getScore(seq, loc);
				max = Math.max(max,
						insert(S, index + 1, newSeq, newScore, newCnt));
			}
		}
		return max;
	}

	public int getColorID(char ch) {
		for (int i = 0; i < 3; i++)
			if (ch == color[i])
				return i;
		return -1;
	}

	public int getScore(String seq, int loc) {
		int score = 0;
		boolean[] tick = new boolean[3];
		for (int i = 0; i < loc; i++)
			tick[getColorID(seq.charAt(i))] = true;
		for (int i = 0; i < 3; i++)
			if (tick[i])
				score++;
		Arrays.fill(tick, false);
		for (int i = loc; i < seq.length(); i++)
			tick[getColorID(seq.charAt(i))] = true;
		for (int i = 0; i < 3; i++)
			if (tick[i])
				score++;
		return score;
	}
}
