public class KingdomAndTrees {
    public static int[] H;

    public int minLevel(int[] heights) {
        H = heights;
        int left = 0, right = 1000000000;
        while (left < right) {
            int mid = (left + right) / 2;
            if (check(mid))
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }

    public boolean check(int x) {
        int mincur = 1;
        for (int i = 0; i < H.length; i++, mincur++) {
            if (mincur - H[i] > x)
                return false;
            mincur = (H[i] > mincur ? Math.max(mincur, H[i] - x) : mincur);
        }
        return true;
    }
}
