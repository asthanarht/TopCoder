package srm616;

public class WakingUp {

	public int maxSleepiness(int[] period, int[] start, int[] volume, int D) {
		int n = period.length;
		int sleep = 0, min = 0;
		long max = 5 * 7 * 8 * 9;
		for (int t = 1; t <= max; t++) {
			sleep += D;
			for (int i = 0; i < n; i++)
				if (start[i] == t) {
					sleep -= volume[i];
					start[i] += period[i];
				}
			min = Math.min(min, sleep);
		}
		if (sleep < 0)
			return -1;
		else
			return -min;
	}

}
