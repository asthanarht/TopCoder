package srm598;

public class FoxAndFencing {
	public String WhoCanWin(int mov1, int mov2, int rng1, int rng2, int d) {
		if (d <= rng1 + mov1)
			return "Ciel";
		else if (mov1 + d <= rng2 + mov2)
			return "Liss";
		else {
			if (mov1 > mov2 && mov1 + rng1 > mov2 * 2 + rng2)
				return "Ciel";
			else if (mov2 > mov1 && mov2 + rng2 > mov1 * 2 + rng1)
				return "Liss";
		}
		return "Draw";
	}
}
