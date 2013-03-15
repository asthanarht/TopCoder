package srm573;

import java.util.Arrays;

public class TeamContest {
    public int worstRank(int[] strength) {
        int n = strength.length;
        int min = Math.min(strength[0], Math.min(strength[1], strength[2]));
        int max = Math.max(strength[0], Math.max(strength[1], strength[2]));
        int sum = min + max;
        n -= 3;
        int[] power = new int[n];
        for(int i=0; i<n; i++)
            power[i] = strength[i+3];
        Arrays.sort(power);
        int result = 1;
        boolean[] check = new boolean[n];
        for (int i = n - 1; i > 0; i--)
            if (!check[i]) {
                check[i] = true;
                boolean found = false;
                for (int j = 0; j < i; j++)
                    if (!check[j] && power[i] + power[j] > sum) {
                        check[j] = true;
                        boolean found2 = false;
                        for (int k = j + 1; k < i; k++)
                            if (!check[k]) {
                                check[k] = true;
                                found2 = true;
                                result++;
                                break;
                            }
                        if (found2)
                            found = true;
                        break;
                    }
                if (!found)
                    break;
            }
        return result;
    }
}
