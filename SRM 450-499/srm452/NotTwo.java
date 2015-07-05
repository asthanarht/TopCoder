public class NotTwo {
    public int maxStones(int width, int height) {
        int count = 0;
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                if (i % 4 < 2 ^ j % 4 > 1)
                    count++;
        return count;
    }
}
