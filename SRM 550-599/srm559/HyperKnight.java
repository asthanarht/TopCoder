public class HyperKnight {
    public long countCells(int a, int b, int numRows, int numColumns, int k) {
        long nr = numRows, nc = numColumns, A = a, B = b;

        long max = Math.max(A, B);
        long min = Math.min(A, B);
        long max2 = max * 2;

        if (k == 8)
            return (nr - max2) * (nc - max2);
        if (k == 6) {
            long result = 0;
            // left & right
            result += (nr - max2) * (max - min) * 2;
            // up & down
            result += (nc - max2) * (max - min) * 2;
            return result;
        }
        if (k == 4) {
            long result = 0;
            // left & right
            result += min * (nr - max2) * 2;
            // up & down
            result += min * (nc - max2) * 2;
            // corner
            result += (max - min) * (max - min) * 4;
            return result;
        }
        if (k == 3)
            return min * (max - min) * 8;
        if (k == 2)
            return min * min * 4;

        return 0;
    }
}
