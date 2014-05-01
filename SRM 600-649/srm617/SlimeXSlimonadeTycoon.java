package srm617;

public class SlimeXSlimonadeTycoon {

	public int sell(int[] morning, int[] customers, int stale_limit) {
		int n = morning.length, res = 0;
		for (int i = 0; i < n; i++) {
			for (int j = Math.max(0, i - stale_limit + 1); j <= i; j++)
				if (morning[j] > 0) {
					int sale = Math.min(customers[i], morning[j]);
					res += sale;
					morning[j] -= sale;
					customers[i] -= sale;
					if (customers[i] == 0)
						break;
				}
		}
		return res;
	}

}
