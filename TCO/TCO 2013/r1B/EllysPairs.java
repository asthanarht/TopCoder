package r1B;

import java.util.Arrays;

public class EllysPairs {
    public int getDifference(int[] knowledge) {
        int max = 0;
        int min = Integer.MAX_VALUE;
        int n = knowledge.length;
        Arrays.sort(knowledge);
        for (int i = 0; i < n / 2; i++) {
            int sum = knowledge[i] + knowledge[n - 1 - i];
            max = Math.max(max, sum);
            min = Math.min(min, sum);
        }
        return max - min;
    }
}
