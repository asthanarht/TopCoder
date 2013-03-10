package r1C;

public class TheArray {
    public int find(int n, int d, int first, int last) {
        if (d == 0)
            return Math.max(first, last);
        int max = Math.max(first, last);
        int min = Math.min(first, last);
        int n1 = (max - min) / d;
        int N = n - n1 - 1;
        min += n1 * d;
        return Math.min(min + (N + 1) / 2 * d, max + N / 2 * d);
    }
}
