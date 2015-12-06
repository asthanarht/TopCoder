public class FloatingMedian {

    public long sumOfMedians(int seed, int mul, int add, int N, int K) {
        long sum = 0;
        int median = (K + 1) / 2;
        SegmentTree tree = new SegmentTree(65536);
        int[] t = new int[N + 1];
        t[0] = seed;
        for (int i = 0; i < N; i++) {
            tree.add(t[i], 1);
            if (i >= K - 1) {
                int bot = -1;
                int top = 65535;
                while (bot + 1 < top) {
                    int mid = (bot + top) / 2;
                    if (tree.sum(0, mid + 1) >= median)
                        top = mid;
                    else
                        bot = mid;
                }
                sum += top;
                tree.add(t[i - K + 1], -1);
            }
            t[i + 1] = (int) ((t[i] * (long) mul + add) % 65536);
        }
        return sum;
    }

}

class SegmentTree {

    private int[] M; // M[0] is never used
    private int n;

    // O(n)
    public SegmentTree(int size) {
        n = size;
        M = new int[n + n];
    }

    // [from, to) O(logn)
    public int sum(int from, int to) {
        int sum = 0;
        for (from += n, to += n; from < to; from >>= 1, to >>= 1) {
            if ((from & 1) > 0)
                sum += M[from++];
            if ((to & 1) > 0)
                sum += M[--to];
        }
        return sum;
    }

    // A[i] += v O(logn)
    public void add(int i, int v) {
        for (M[i += n] += v; i > 1;)
            M[i >>= 1] += v;
    }

}