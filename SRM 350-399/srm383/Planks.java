package srm383;

import java.util.Arrays;

public class Planks {
    public int makeSimilar(int[] lengths, int costPerCut, int woodValue) {
        Arrays.sort(lengths);
        int n = lengths.length;
        int max = 0;
        for (int len = 1; len <= lengths[n - 1]; len++) {
            int money = 0;
            for (int i = 0; i < n; i++)
                if (lengths[i] >= len) {
                    int seg = lengths[i] / len;
                    int cut = seg;
                    if (lengths[i] % len == 0)
                        cut--;
                    int m = len * seg * woodValue - cut * costPerCut;
                    money += m > 0 ? m : 0;
                }
            max = Math.max(max, money);
        }
        return max;
    }
}
