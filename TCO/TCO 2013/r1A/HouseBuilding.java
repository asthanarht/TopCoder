public class HouseBuilding {
    public int getMinimum(String[] area) {
        int result = Integer.MAX_VALUE;
        int n = area.length, m = area[0].length();
        for (int level = 0; level < 9; level++) {
            int total = 0;
            for (int i = 0; i < n; i++)
                for (int j = 0; j < m; j++) {
                    int num = area[i].charAt(j) - '0';
                    if (num > level + 1)
                        total += num - level - 1;
                    else if (num < level)
                        total += level - num;
                }
            result = Math.min(result, total);
        }
        return result;
    }
}
