package srm338;

public class ImprovingStatistics {
    public int howManyGames(int played, int won) {
        long t = played, w = won;
        long p = w * 100 / t;
        if (p >= 99)
            return -1;
        p++;
        long n = (p * t - 100 * w) / (100 - p);
        if ((w + n) * 100 / (t + n) == p)
            return (int) n;
        else
            return (int) (n + 1);
    }
}
