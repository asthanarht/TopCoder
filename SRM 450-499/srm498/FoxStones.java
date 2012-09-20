package srm498;

import java.util.ArrayList;
import java.util.HashMap;

public class FoxStones {
	public static final int MOD = 1000000009;

	public int getCount(int N, int M, int[] sx, int[] sy) {
		long[] P = new long[40001];
		P[0] = 1;
		for (int i = 1; i <= 40000; i++) {
			P[i] = P[i - 1] * i;
			P[i] %= MOD;
		}
		HashMap<ArrayList<Integer>, Integer> map = new HashMap<ArrayList<Integer>, Integer>();
		for (int i = 1; i <= N; i++)
			for (int j = 1; j <= M; j++) {
				ArrayList<Integer> sign = new ArrayList<Integer>();
				for (int k = 0; k < sx.length; k++)
					sign.add(Math.max(Math.abs(sx[k] - i), Math.abs(sy[k] - j)));
				if (map.get(sign) == null)
					map.put(sign, 1);
				else
					map.put(sign, map.get(sign) + 1);
			}
		long result = 1;
		for (int count : map.values()) {
			result *= P[count];
			result %= MOD;
		}
		return (int) result;
	}
}
