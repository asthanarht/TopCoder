package srm331;

import java.util.Arrays;

public class Shopping {
	public int minNumber(int X, int[] values) {
		Arrays.sort(values);
		if (values[0] != 1)
			return -1;
		int count = 0;
		for (int sum = 0; sum < X;) {
			for (int i = values.length - 1; i >= 0; i--)
				if (values[i] <= sum + 1) {
					sum += values[i];
					count++;
					break;
				}
		}
		return count;
	}
}
