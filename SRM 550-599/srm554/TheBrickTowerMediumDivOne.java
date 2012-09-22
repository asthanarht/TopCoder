package srm554;

public class TheBrickTowerMediumDivOne {
    public int[] find(int[] heights) {
        int n = heights.length;
        int[] result = new int[n];
        if (n == 1)
            return result;

        int left = 0, right = n - 1;
        int mini = 0;
        boolean[] tick = new boolean[n];
        for (int i = 0; i < n - 2; i++) {
            int max = 0, maxi = 48;
            for (int j = n - 1; j >= mini; j--)
                if (!tick[j] && heights[j] > max) {
                    max = heights[j];
                    maxi = j;
                }
            if (max == heights[mini]) {
                result[left++] = mini;
                tick[mini] = true;
                while (tick[mini])
                    mini++;
            }
            else {
                result[right--] = maxi;
                tick[maxi] = true;
            }
        }
        for (int i = 0; i < n; i++)
            if (!tick[i])
                result[left++] = i;
        return result;
    }
}