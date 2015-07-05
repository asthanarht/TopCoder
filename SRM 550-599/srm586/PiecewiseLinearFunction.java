public class PiecewiseLinearFunction {
    public int maximumSolutions(int[] Y) {
        for (int j = 1; j < Y.length; j++)
            if (Y[j] == Y[j - 1])
                return -1;
        int max = 0;
        for (int i = 0; i < Y.length; i++) {
            max = Math.max(max, count(Y, Y[i] - 0.5));
            max = Math.max(max, count(Y, Y[i]));
            max = Math.max(max, count(Y, Y[i] + 0.5));
        }
        return max;
    }

    private int count(int[] Y, double y) {
        int cnt = 0;
        for (int j = 0; j < Y.length; j++)
            if (Y[j] == y)
                cnt++;
        for (int j = 1; j < Y.length; j++) {
            int low = Math.min(Y[j], Y[j - 1]);
            int high = Math.max(Y[j], Y[j - 1]);
            if (low < y && y < high)
                cnt++;
        }
        return cnt;
    }
}
