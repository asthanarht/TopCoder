package srm622;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Ethernet {
    private HashMap<Integer, ArrayList<Integer>> children;
    int[][] dp = new int[51][501];
    int[] dis;
    int maxDist;

    public int connect(int[] parent, int[] dist, int maxDist) {
        children = new HashMap<Integer, ArrayList<Integer>>();
        for (int i = 0; i <= parent.length; i++)
            children.put(i, new ArrayList<Integer>());
        for (int i = 0; i < parent.length; i++)
            children.get(parent[i]).add(i + 1);
        for (int i = 0; i < 51; i++)
            Arrays.fill(dp[i], 100);
        dis = dist;
        this.maxDist = maxDist;
        return solve(0, maxDist);
    }

    private int solve(int root, int maxD) {
        if (dp[root][maxD] < 100)
            return dp[root][maxD];
        int res = 1;
        for (Integer c : children.get(root))
            res += solve(c, maxDist);
        for (Integer c1 : children.get(root)) {
            for (int len = dis[c1 - 1]; len <= maxD; len++) {
                int cnt = solve(c1, len - dis[c1 - 1]);
                // int rem = Math.min(len, maxD - len);
                int rem = Math.min(len, maxDist - len);
                for (Integer c2 : children.get(root))
                    if (c2 != c1) {
                        int without = solve(c2, maxDist);
                        if (dis[c2 - 1] > rem)
                            cnt += without;
                        else {
                            int within = solve(c2, rem - dis[c2 - 1]) - 1;
                            cnt += Math.min(within, without);
                        }
                    }
                res = Math.min(res, cnt);
            }
        }
        dp[root][maxD] = res;
        return res;
    }

}
