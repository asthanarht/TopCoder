import java.util.Arrays;

public class HeightRound {
    public int[] getBestRound(int[] heights) {
        int n = heights.length;
        Arrays.sort(heights);
        if (n <= 3)
            return heights;
        int diff = 0;
        for (int i = 0; i < n - 2; i++)
            diff = Math.max(diff, heights[i + 2] - heights[i]);
        boolean[] rear = new boolean[n];
        loop: for (int i = 0; i < n - 1;) {
            for (int j = i + 1; j < n; j++)
                if (heights[j] - heights[i] > diff) {
                    i = j - 1;
                    rear[i] = true;
                    continue loop;
                }
            break;
        }
        int[] res = new int[n];
        int index = 0;
        for (int i = 0; i < n; i++)
            if (!rear[i])
                res[index++] = heights[i];
        for (int i = n - 1; i >= 0; i--)
            if (rear[i])
                res[index++] = heights[i];
        return res;
    }
}
