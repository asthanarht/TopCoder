import java.util.Arrays;

public class SpaceWarDiv1 {
    public long minimalFatigue(int[] magicalGirlStrength, int[] enemyStrength,
            long[] enemyCount) {
        int n = magicalGirlStrength.length, m = enemyStrength.length;
        Arrays.sort(magicalGirlStrength);
        long[] able = new long[n];
        for (int i = 0; i < m; i++) {
            boolean beat = false;
            for (int j = 0; j < n; j++)
                if (magicalGirlStrength[j] >= enemyStrength[i]) {
                    able[j] += enemyCount[i];
                    beat = true;
                    break;
                }
            if (!beat)
                return -1;
        }
        boolean found = true;
        while (found) {
            found = false;
            long max = able[n - 1];
            int maxi = n - 1;
            for (int i = n - 2; i >= 0; i--)
                if (able[i] > max) {
                    max = able[i];
                    maxi = i;
                }
            if (maxi == n - 1)
                return max;
            long second = able[n - 1];
            for (int i = n - 2; i > maxi; i--)
                if (able[i] > second)
                    second = able[i];
            for (int i = maxi + 1; i < n; i++)
                if (able[i] < second) {
                    long move = Math.min(able[maxi] - second, second - able[i]);
                    if (move > 0) {
                        found = true;
                        able[maxi] -= move;
                        able[i] += move;
                    }
                }
            if (able[maxi] > second)
                for (int i = maxi + 1; i < n; i++)
                    if (able[i] == second) {
                        long move = (able[maxi] - second) / 2;
                        if (move > 0) {
                            found = true;
                            able[maxi] -= move;
                            able[i] += move;
                            break;
                        }
                    }
        }
        long max = 0;
        for (int i = 0; i < n; i++)
            max = Math.max(max, able[i]);
        return max;
    }
}
