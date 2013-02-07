package srm568;

public class BallsSeparating {
    public int minOperations(int[] red, int[] green, int[] blue) {
        int n = red.length;
        int min = Integer.MAX_VALUE;
        if (n < 3)
            return -1;
        int total = 0;
        for (int i = 0; i < n; i++)
            total += red[i] + green[i] + blue[i];
        for (int r = 0; r < n; r++)
            for (int g = 0; g < n; g++)
                for (int b = 0; b < n; b++)
                    if (r != g && r != b && g != b) {
                        int move = total;
                        for (int i = 0; i < n; i++)
                            if (i == r)
                                move -= red[i];
                            else if (i == g)
                                move -= green[i];
                            else if (i == b)
                                move -= blue[i];
                            else
                                move -= Math.max(red[i],
                                        Math.max(green[i], blue[i]));
                        min = Math.min(min, move);
                    }
        return min;
    }
}
