package srm305;

import java.util.Arrays;

public class UnfairDivision {
    public int albertsShare(int[] assets) {
        int max = 0;// albert
        for (int i = 1; i < assets.length; i++)
            max = Math.max(max, cut(assets, i));
        return max;
    }

    private int cut(int[] assets, int one) {
        int max = 0;// betty
        int min = Integer.MAX_VALUE;// albert
        for (int i = 1; i < assets.length; i++)
            if (i != one) {
                int smaller = Math.min(i, one);
                int biger = Math.max(i, one);
                int[] sum = new int[3];
                for (int j = 0; j < smaller; j++)
                    sum[0] += assets[j];
                for (int j = smaller; j < biger; j++)
                    sum[1] += assets[j];
                for (int j = biger; j < assets.length; j++)
                    sum[2] += assets[j];
                Arrays.sort(sum);
                if (sum[1] > max) {
                    max = sum[1];
                    min = sum[0];
                }
                else if (sum[1] == max)
                    min = Math.min(min, sum[0]);
            }
        return min;
    }
}
