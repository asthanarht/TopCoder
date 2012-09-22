package srm340;

import java.util.HashMap;

public class CsCourses {
    private static HashMap<Integer, Integer>[][][] dp;
    private static int[] tv, pv, e;
    private static int n, sb;

    @SuppressWarnings("unchecked")
    public static int[] getOrder(int[] theoreticalValue, int[] practicalValue,
            int[] expire, int skillBound) {
        n = theoreticalValue.length;
        tv = theoreticalValue;
        pv = practicalValue;
        e = expire;
        sb = skillBound;
        dp = new HashMap[n + 1][51][51];
        dp[0][0][0] = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++)
            for (int t = 0; t <= 50; t++)
                for (int p = 0; p <= 50; p++)
                    if (dp[i][t][p] != null) {
                        for (int j = 0; j < n; j++)
                            if (i < e[j] && t >= tv[j] - 1 && p >= pv[j] - 1
                                    && !dp[i][t][p].values().contains(j)) {
                                HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
                                for (Integer step : dp[i][t][p].keySet())
                                    hm.put(step, dp[i][t][p].get(step));
                                hm.put(i + 1, j);
                                dp[i + 1][Math.max(t, tv[j])][Math
                                        .max(p, pv[j])] = getBetter(
                                        dp[i + 1][Math.max(t, tv[j])][Math.max(
                                                p, pv[j])], hm);
                            }
                    }
        HashMap<Integer, Integer> best = null;
        for (int i = sb; i <= n; i++)
            for (int t = sb; t <= 50; t++)
                for (int p = sb; p <= 50; p++)
                    best = getBetter(best, dp[i][t][p]);
        if (best == null)
            return new int[0];
        int[] res = new int[best.size()];
        for (int i = 0; i < res.length; i++)
            res[i] = best.get(i + 1);
        return res;
    }

    private static HashMap<Integer, Integer> getBetter(
            HashMap<Integer, Integer> hm1, HashMap<Integer, Integer> hm2) {
        if (hm1 == null && hm2 != null)
            return hm2;
        else if (hm1 != null && hm2 == null)
            return hm1;
        else if (hm1 == null && hm2 == null)
            return null;
        else {
            if (hm1.size() < hm2.size())
                return hm1;
            else if (hm1.size() > hm2.size())
                return hm2;
            else {
                // for (Integer step : hm1.keySet())
                for (int step = 1; step <= hm1.size(); step++)
                    if (hm1.get(step) > hm2.get(step))
                        return hm2;
                    else if (hm1.get(step) < hm2.get(step))
                        return hm1;
                return hm1;
            }
        }
    }
}