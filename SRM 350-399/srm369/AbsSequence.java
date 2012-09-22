package srm369;

public class AbsSequence {
    public String[] getElements(String first, String second, String[] indices) {
        String[] res = new String[indices.length];
        long a = Long.parseLong(first), b = Long.parseLong(second);
        for (int i = 0; i < res.length; i++)
            res[i] = "" + solve(a, b, Long.parseLong(indices[i]));
        return res;
    }

    private long solve(long a, long b, long index) {
        if (index == 0)
            return a;
        else if (index == 1)
            return b;
        else if (b == 0)
            return index % 3 == 1 ? 0 : a;
        else if (a > b) {
            long c = a - b;
            long n = a / c;
            n -= n % 2 == 0 ? 1 : 0;
            long max = n + (n + 1) / 2;
            if (index > max - 1)
                return solve(a - c * n, c, index - max + 1);
            else
                return index % 3 == 2 ? c : a - c * (index - (index + 1) / 3);
        }
        else
            return solve(b, b - a, index - 1);
    }
}
