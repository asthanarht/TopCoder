public class TimeTravellingGardener {
    private final double EPS = 1e-11;

    public int determineUsage(int[] distance, int[] height) {
        int n = height.length;
        int[] loc = new int[n];
        for (int i = 1; i < n; i++)
            loc[i] = loc[i - 1] + distance[i - 1];
        int min = n - 1;
        for (int tree1 = 0; tree1 < n; tree1++)
            for (int tree2 = tree1 + 1; tree2 < n; tree2++) {
                double dis = loc[tree2] - loc[tree1];
                double h1 = height[tree1], h2 = height[tree2];
                double unitChange = (h2 - h1) / dis;
                int count = n;
                for (int i = 0; i < n; i++) {
                    double expect = h1 + (loc[i] - loc[tree1]) * unitChange;
                    if (expect < -EPS || height[i] + EPS < expect) {
                        count += 100;
                        break;
                    }
                    if (height[i] - EPS < expect)
                        count--;
                }
                min = Math.min(min, count);
            }
        return min;
    }
}
