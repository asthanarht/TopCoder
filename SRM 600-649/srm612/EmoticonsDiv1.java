package srm612;

import java.util.Arrays;

public class EmoticonsDiv1 {
	private static int[] steps = new int[1001];

	public static int printSmiles(int smiles) {
		Arrays.fill(steps, Integer.MAX_VALUE);
		steps[1] = 0;
		for (int num = 1; num < smiles; num++) {
			int t = 1;
			while (num + num * (t - 1) < smiles) {
				int from = num + num * (t - 1) + 1;
				int to = num + num * t;
				for (int i = from; i <= Math.min(to, smiles); i++)
					steps[i] = Math
					        .min(steps[i], steps[num] + 1 + t + (to - i));
				t++;
			}
		}
		return steps[smiles];
	}
}
