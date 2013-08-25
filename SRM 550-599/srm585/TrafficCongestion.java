package srm585;

public class TrafficCongestion {
    public int theMinCars(int treeHeight) {
        long mod = 1000000007;
        long result = 0;
        if (treeHeight % 2 == 0) {
            result = 1;
            long nodes = 2;
            for (int level = 1; level <= treeHeight; level += 2) {
                result += nodes;
                result %= mod;
                nodes *= 4;
                nodes %= mod;
            }
        }
        else {
            long nodes = 1;
            for (int level = 0; level <= treeHeight; level += 2) {
                result += nodes;
                result %= mod;
                nodes *= 4;
                nodes %= mod;
            }
        }
        return (int) result;
    }
}
