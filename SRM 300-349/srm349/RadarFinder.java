public class RadarFinder {
    public int possibleLocations(int x1, int y1, int r1, int x2, int y2, int r2) {
        if (x1 == x2 && y1 == y2 && r1 == r2)
            return -1;
        else {
            long a = Math.abs(r1 - r2), b = Math.abs(r1 + r2), x = Math.abs(x1
                    - x2), y = Math.abs(y1 - y2);
            long dis = x * x + y * y;
            if (dis > a * a && dis < b * b)
                return 2;
            else if (dis == a * a || dis == b * b)
                return 1;
            return 0;
        }
    }
}
