package srm345;

import java.util.Arrays;

public class StoneGame {
    public int getScore(int[] treasure, int[] stones) {
        int n = treasure.length, sum = 0;
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            sum += stones[i];
            if (stones[i] == 1)
                array[i] = treasure[i];
        }
        Arrays.sort(array);
        int res = 0;
        for (int i = n - 1; i >= 0; i -= 2)
            res += array[i];
        /*
         * if total stones are of odd number, then the first mover could get all
         * the other treasures, otherwise, the second mover get all of them.
         */
        if (sum % 2 == 1)
            for (int i = 0; i < n; i++)
                if (stones[i] != 1)
                    res += treasure[i];
        return res;
    }
}