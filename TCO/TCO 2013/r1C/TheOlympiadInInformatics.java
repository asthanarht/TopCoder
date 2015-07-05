import java.util.Arrays;

public class TheOlympiadInInformatics {
    public int find(int[] sums, int k) {
        Arrays.sort(sums);
        int n = sums.length;
        int min = 0, max = sums[n - 1];
        while (min + 1 < max) {
            int mid = (max + min) / 2;
            if (check(sums, k, mid))
                max = mid; // max must be fine
            else
                min = mid + 1; // min may be fine, may be wrong
        }
        if (check(sums, k, min))
            return min;
        return max;
    }

    private boolean check(int[] sums, int k, int score) {
        long count = 0; // mind overflow
        for (int i = 0; i < sums.length; i++)
            count += sums[i] / (score + 1);
        if (count <= k)
            return true;
        return false;
    }
}