package srm146;

public class RectangularGrid {
    public long countRectangles(int width, int height) {
        int[] w = new int[width + 1];
        int[] h = new int[height + 1];
        for (int i = 1; i <= width; i++)
            w[i] = width + 1 - i;
        for (int j = 1; j <= height; j++)
            h[j] = height + 1 - j;
        long count = 0;
        for (int i = 1; i <= width; i++)
            for (int j = 1; j <= height; j++)
                if (i != j)
                    count += w[i] * h[j];
        return count;
    }
}
