package srm617;

public class PieOrDolphin {

	public int[] Distribute(int[] choice1, int[] choice2) {
		int n = choice1.length;
		int[] degree = new int[50];
		for (int i = 0; i < n; i++) {
			degree[choice1[i]]++;
			degree[choice2[i]]++;
		}
		int[] ans = new int[n];
		for (int i = 0; i < 50; i++) {
			boolean direction = false;
			while (degree[i] > 0) {
				direction = !direction;
				int cur = i;
				next: while (degree[cur] > 0) {
					for (int j = 0; j < n; j++)
						if (ans[j] == 0 && choice1[j] == cur) {
							ans[j] = (direction ? 1 : 2);
							degree[cur]--;
							cur = choice2[j];
							degree[cur]--;
							continue next;
						}
					for (int j = 0; j < n; j++)
						if (ans[j] == 0 && choice2[j] == cur) {
							ans[j] = (direction ? 2 : 1);
							degree[cur]--;
							cur = choice1[j];
							degree[cur]--;
							continue next;
						}
				}
			}
		}
		return ans;
	}

}
