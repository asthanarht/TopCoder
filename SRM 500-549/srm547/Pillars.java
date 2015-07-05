public class Pillars {
    public double getExpectedLength(int w, int x, int y) {
        long count = 0, ww = w * w;
        double total = 0;
        int min = Math.min(x, y), max = Math.max(x, y);
        for (long abs = 0; abs < max; abs++) {
            int num = 0;
            if (max > abs)
                num += Math.min(min + abs, max) - abs;
            if (min > abs)
                num += min - abs;
            if (abs == 0)
                num /= 2;
            if (num > 0) {
                double len = Math.sqrt(ww + abs * abs);
                count += num;
                total += len * num;
            }
        }
        return total / count;
    }
}
