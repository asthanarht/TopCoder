import java.util.Arrays;
import java.util.LinkedList;

public class OrderOfOperations {

	public int minTime(String[] s) {
		int n = s.length;
		int[] bits = new int[n];
		LinkedList<Integer> queue = new LinkedList<Integer>();
		boolean[] used = new boolean[1 << 20];
		int[] dp = new int[1 << 20];
		Arrays.fill(dp, 400);
		int all = 0;
		for (int i = 0; i < n; i++) {
			bits[i] = Integer.parseInt(s[i], 2);
			if (!used[bits[i]]) {
				queue.add(bits[i]);
				used[bits[i]] = true;
			}
			dp[bits[i]] = Integer.bitCount(bits[i]);
			dp[bits[i]] *= dp[bits[i]];
			all |= bits[i];
		}
		while (queue.size() > 0) {
			LinkedList<Integer> next = new LinkedList<Integer>();
			Arrays.fill(used, false);
			while (queue.size() > 0) {
				int state = queue.poll();
				for (Integer ins : bits) {
					int nstate = ins | state;
					if (nstate != state) {
						int access = Integer.bitCount(nstate)
								- Integer.bitCount(state);
						int cost = dp[state] + access * access;
						if (cost < dp[nstate]) {
							dp[nstate] = cost;
							if (!used[nstate]) {
								next.add(nstate);
								used[nstate] = true;
							}
						}
					}
				}
			}
			queue = next;
		}
		return dp[all];
	}

}
