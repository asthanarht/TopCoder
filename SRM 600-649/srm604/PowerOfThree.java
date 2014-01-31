package srm604;

public class PowerOfThree {
	public String ableToGet(int x, int y) {
		x = Math.abs(x);
		y = Math.abs(y);
		while (x > 0 || y > 0) {
			if (x % 3 == 0 && y % 3 == 0)
				return "Impossible";
			else if (x % 3 != 0 && y % 3 != 0)
				return "Impossible";
			else if (x % 3 == 0) {
				if (y % 3 == 1)
					y--;
				else
					y++;
			} else {
				if (x % 3 == 1)
					x--;
				else
					x++;
			}
			x /= 3;
			y /= 3;
		}
		return "Possible";
	}
}
