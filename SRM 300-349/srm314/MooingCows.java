package srm314;

import java.util.ArrayList;

public class MooingCows {
    public int dissatisfaction(String[] farmland) {
        ArrayList<Integer> loc = new ArrayList<Integer>();
        for (int i = 0; i < farmland.length; i++)
            for (int j = 0; j < farmland[0].length(); j++)
                if (farmland[i].charAt(j) == 'C') {
                    loc.add(i);
                    loc.add(j);
                }
        int n = loc.size() / 2;
        int[] dis = new int[n];
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++) {
                int index1 = i * 2;
                int index2 = j * 2;
                int num = (int) (Math.pow(loc.get(index1) - loc.get(index2), 2) + Math
                        .pow(loc.get(index1 + 1) - loc.get(index2 + 1), 2));
                dis[i] += num;
                dis[j] += num;
            }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++)
            min = Math.min(min, dis[i]);
        return min;
    }
}
