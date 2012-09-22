package srm452;

public class EggCartons {
    public int minCartons(int n) {
        int c8 = n / 8;
        while (c8 >= 0) {
            int c6 = n - c8 * 8;
            if (c6 % 6 != 0) {
                c8--;
                continue;
            }
            else
                return c8 + c6 / 6;
        }
        return -1;
    }
}
