import java.util.Arrays;

public class BinPacking {
	public int minBins(int[] item) {
		int res = item.length;
		Arrays.sort(item);
		for (int pairFrom = 0; pairFrom <= item.length; pairFrom += 3)
			if (pairFrom == 0 || item[pairFrom - 1] == 100) {
				int cnt = pairFrom / 3;
				int start = pairFrom, end = item.length - 1;
				while (start <= end)
					if (item[start] + item[end] <= 300) {
						cnt++;
						start++;
						end--;
					} else {
						cnt++;
						end--;
					}
				res = Math.min(res, cnt);
			}
		return res;
	}
}