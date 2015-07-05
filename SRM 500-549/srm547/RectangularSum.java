public class RectangularSum {
    public long minimalArea(int height, int width, long S) {
        long minArea = Long.MAX_VALUE, area, r, c, minus, a1, a2;
        S *= 2;
        for (long w = 1; w <= width; w++)
            if (S % w == 0) {
                a1 = S / w;
                for (long h = 1; h <= height && h <= a1; h++)
                    if (a1 % h == 0) {
                        a2 = a1 / h;
                        minus = (w - 1) + (h - 1) * width;
                        if (a2 < minus)
                            break;
                        a2 -= minus;
                        if (a2 % 2 != 0)
                            continue;
                        a2 /= 2;
                        r = a2 / width;
                        c = a2 % width;
                        if (r + h <= height && c + w <= width) {
                            area = w * h;
                            if (area < minArea)
                                minArea = area;
                        }
                    }
            }
        return minArea == Long.MAX_VALUE ? -1 : minArea;
    }
}