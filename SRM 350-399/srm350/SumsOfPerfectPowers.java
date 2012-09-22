package srm350;

import java.util.ArrayList;

public class SumsOfPerfectPowers {
    public int howMany(int lowerBound, int upperBound) {
        boolean[] tick = new boolean[5000001];
        ArrayList<Integer> list = new ArrayList<Integer>();
        tick[0] = true;
        tick[1] = true;
        list.add(0);
        list.add(1);
        for (int i = 2; i <= Math.sqrt(5000000); i++) {
            int multi = i;
            while (multi <= 5000000 / i) {
                multi *= i;
                if (!tick[multi])
                    list.add(multi);
                tick[multi] = true;
            }
        }

        for (int i = 0; i < list.size(); i++)
            for (int j = 0; j <= i; j++)
                if (list.get(i) + list.get(j) < 5000001)
                    tick[list.get(i) + list.get(j)] = true;

        int count = 0;
        for (int i = lowerBound; i <= upperBound; i++)
            if (tick[i])
                count++;
        return count;
    }
}