import java.util.Arrays;

public class PowersOfTwo {
	public long count(long[] powers) {
		long[] value = new long[51];
		value[0] = 1;
		for (int i = 1; i <= 50; i++)
			value[i] = value[i - 1] * 2;

		int[] cnt = new int[51];
		for (int i = 0; i < powers.length; i++)
			cnt[Arrays.binarySearch(value, powers[i])]++;

		for (int i = 0; i + 1 <= 50; i++)
			while (cnt[i] > 2) {
				cnt[i] -= 2;
				cnt[i + 1]++;
			}

		long[][] dp = new long[52][102]; // ways[i + 1][j] 2^i appear j times
		dp[0][0] = 1;
		for (int i = 0; i <= 50; i++)
			for (int j = 0; j <= 50; j++) {
				// missing number of 2^i need to be obtained from 2^(i-1)
				int missing = Math.max(0, j - cnt[i]);
				int n = missing * 2; // the number of 2^(i-1) needed
				dp[i + 1][j] = dp[i][n] + dp[i][n + 1];
			}

		long res = 0;
		for (int i = 0; i <= 50; i++)
			res += dp[51][i];
		return res;
	}
}
