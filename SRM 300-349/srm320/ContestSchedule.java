package srm320;

import java.util.Arrays;

public class ContestSchedule {
    public double expectedWinnings(String[] contests) {
        int n = contests.length;
        Contest[] cs = new Contest[n];
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            String[] info = contests[i].split(" ");
            cs[i] = new Contest(Integer.parseInt(info[0]),
                    Integer.parseInt(info[1]), Integer.parseInt(info[2]));
        }
        Arrays.sort(cs);
        for (int i = 0; i < n; i++) {
            dp[i] = cs[i].p;
            for (int j = 0; j < i; j++)
                if (cs[j].t <= cs[i].s)
                    dp[i] = Math.max(dp[j] + cs[i].p, dp[i]);
        }
        int max = 0;
        for (int i = 0; i < n; i++)
            max = Math.max(max, dp[i]);
        return max / 100.0;
    }
}

class Contest implements Comparable<Contest> {
    int s;
    int t;
    int p;

    public Contest(int s, int t, int p) {
        super();
        this.s = s;
        this.t = t;
        this.p = p;
    }

    public int compareTo(Contest c) {
        if (this.s != c.s)
            return this.s < c.s ? -1 : 1;
        if (this.t == c.t)
            return 0;
        return this.t < c.t ? -1 : 1;
    }
}
