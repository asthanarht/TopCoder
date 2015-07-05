public class PointErasingTwo {
    public int getMaximum(int[] y) {
        int max = 0;
        for (int i = 0; i < y.length - 1; i++)
            for (int j = i + 1; j < y.length; j++) {
                int y1 = y[i] >= y[j] ? y[j] : y[i];
                int y2 = y[i] >= y[j] ? y[i] : y[j];
                if (y1 == y2)
                    continue;
                int count = 0;
                for (int k = i + 1; k < j; k++)
                    if (y[k] > y1 && y[k] < y2)
                        count++;
                if (count > max)
                    max = count;
            }
        return max;
    }
}
