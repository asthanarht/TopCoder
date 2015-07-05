import java.util.Arrays;

// Assume F-f1+r1-f2+r2 is valid;
// Assume F-f2+r2-f1+r2 is not valid;
// ==> F-f1+r1-f2>=0 && F-f2+r2-f1<0
// ==> F+r1>=f1+f2>F+r2
// ==> r1>r2
// ==> order in descending order of r

public class AlbertoTheAviator {
	public int MaximumFlights(int F, int[] duration, int[] refuel) {
		int n = duration.length;
		Mission[] mission = new Mission[n];
		for (int i = 0; i < n; i++)
			mission[i] = new Mission(duration[i], refuel[i]);
		Arrays.sort(mission);

		int[][] dp = new int[n][n + 1];
		for (int i = 0; i < n; i++)
			for (int j = 0; j <= n; j++)
				dp[i][j] = -1;
		dp[0][0] = F;
		if (F >= mission[0].duration)
			dp[0][1] = F - mission[0].lose;

		for (int i = 0; i < n - 1; i++)
			for (int done = 0; done < n; done++) {
				dp[i + 1][done] = Math.max(dp[i + 1][done], dp[i][done]);
				if (dp[i][done] >= mission[i + 1].duration) {
					dp[i + 1][done + 1] = Math.max(dp[i + 1][done + 1],
					        dp[i][done] - mission[i + 1].lose);
				}
			}

		int res = 0;
		for (int done = 0; done <= n; done++)
			if (dp[n - 1][done] >= 0)
				res = done;
		return res;
	}
}

class Mission implements Comparable<Mission> {
	int duration;
	int refuel;
	int lose;

	public Mission(int duration, int refuel) {
		super();
		this.duration = duration;
		this.refuel = refuel;
		lose = duration - refuel;
	}

	public int compareTo(Mission m) {
		return m.refuel - this.refuel;
	}
}
