package srm616;

import java.util.Arrays;

// query twice
// times (max):  22233333
// first query:  1_1122_2
// second query: _1121_22

// query three times
// times (max):  22222223333333333333333333
// first query:  1__1_1111_22_1221122__2_22
// second query: _1_11_12_11_2212121_2_22_2
// third query:  __1_111_22_11221211__2_222
public class ColorfulCoins {

	public int minQueries(long[] values) {
		int n = values.length;
		long[] times = new long[n - 1];
		for (int i = 0; i < n - 1; i++)
			times[i] = values[i + 1] / values[i];
		Arrays.sort(times);

		for (int res = 1; res <= 5; res++) {
			boolean ok = true;
			for (int i = 0; i < n - 1; i++) {
				int num = i + 1;
				for (int j = 0; j < res; j++)
					num /= times[i];
				if (num > 0)
					ok = false;
			}
			if (ok)
				return res;
		}
		return 6;
	}

}
