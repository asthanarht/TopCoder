package srm554;

public class TheBrickTowerEasyDivOne {
	public int find(int redCount, int redHeight, int blueCount, int blueHeight) {
		if (redHeight == blueHeight)
			return Math.min(blueCount + Math.min(redCount, blueCount + 1),
					redCount + Math.min(blueCount, redCount + 1));
		if (redCount >= blueCount)
			return blueCount * 2 + Math.min(redCount, blueCount + 1);
		return redCount * 2 + Math.min(blueCount, redCount + 1);
	}
}
