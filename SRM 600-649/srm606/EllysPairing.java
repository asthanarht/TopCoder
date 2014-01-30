package srm606;

import java.util.HashSet;

public class EllysPairing {
	public int getMax(int M, int[] count, int[] first, int[] mult, int[] add) {
		HashSet<Integer> set = getMaxR(M, count, first, mult, add);
		int[] cnt = new int[524288];

		int n = count.length;
		int max = 0, total = 0, name, key, key2;
		for (int i = 0; i < n; i++) {
			total += count[i];

			name = first[i];

			key = name & 8191;
			if (set.contains(key)) {
				key2 = name >> 13;
				cnt[key2]++;
				max = Math.max(max, cnt[key2]);
			}

			for (int j = 1; j < count[i]; j++) {
				name = (name * mult[i] + add[i]) & (M - 1);

				key = name & 8191;
				if (set.contains(key)) {
					key2 = name >> 13;
					cnt[key2]++;
					max = Math.max(max, cnt[key2]);
				}
			}
		}
		int other = total - max;
		if (other > max)
			return max + (other - max) / 2;
		return other;
	}

	public HashSet<Integer> getMaxR(int M, int[] count, int[] first,
			int[] mult, int[] add) {
		int[] cnt = new int[8192];
		int n = count.length;
		int max = 0, name, next, key;
		for (int i = 0; i < n; i++) {
			name = first[i];

			key = name & 8191;
			cnt[key]++;
			max = Math.max(max, cnt[key]);

			for (int j = 1; j < count[i]; j++) {
				next = (name * mult[i] + add[i]) & (M - 1);
				name = next;

				key = name & 8191;
				cnt[key]++;
				max = Math.max(max, cnt[key]);
			}
		}

		HashSet<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < 8192; i++)
			if (cnt[i] == max)
				set.add(i);
		return set;
	}

}