package srm633;

public class PeriodicJumping {

	public int minimalTime(int x, int[] jumpLengths) {
		int n = jumpLengths.length;
		long period = 0;
		for (int i = 0; i < n; i++)
			period += jumpLengths[i];

		x = Math.abs(x);
		long min = 0, max = 0;
		int move = 0;
		int i = 0;
		while (true) {
			if (x >= min && x <= max)
				return move;
			if (min == 0 && max > period) {
				int cir = (int) ((x - max) / period - 2);
				if (cir > 0) {
					move += n * cir;
					max += period * cir;
				}
			}
			if (jumpLengths[i] < min)
				min -= jumpLengths[i];
			else if (jumpLengths[i] > max)
				min = jumpLengths[i] - max;
			else
				min = 0;
			max += jumpLengths[i];
			move++;
			i++;
			if (i == n)
				i = 0;
		}
	}
}
