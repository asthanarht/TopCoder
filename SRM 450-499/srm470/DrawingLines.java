public class DrawingLines {
    public double countLineCrossings(int n, int[] startDot, int[] endDot) {
        double result = 0;
        int m = startDot.length;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < m; j++)
                if (startDot[i] < startDot[j] && endDot[i] > endDot[j])
                    result++;
        boolean[] start = new boolean[n + 1];
        boolean[] end = new boolean[n + 1];
        for (int i = 0; i < m; i++) {
            start[startDot[i]] = true;
            end[endDot[i]] = true;
        }
        for (int i = 0; i < m; i++) {
            long startLeft = startDot[i] - 1;
            long startRight = n - startDot[i];
            long endLeft = endDot[i] - 1;
            long endRight = n - endDot[i];
            for (int j = 0; j < m; j++) {
                if (startDot[j] < startDot[i])
                    startLeft--;
                else if (startDot[j] > startDot[i])
                    startRight--;
                if (endDot[j] < endDot[i])
                    endLeft--;
                else if (endDot[j] > endDot[i])
                    endRight--;
            }
            result += (startLeft * endRight + startRight * endLeft)
                    / (double) (n - m);
        }
        result += (n - m) * (n - m - 1) / 4.0;
        return result;
    }
}
