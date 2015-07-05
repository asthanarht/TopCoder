import java.util.Arrays;

public class GroupWork {
    public long bestGroup(int[] p, int[] s) {
        int n = p.length;
        long max = 0, K = 0;
        Set[] sets = new Set[n];
        for (int i = 0; i < n; i++)
            sets[i] = new Set(p[i], s[i]);
        Arrays.sort(sets);
        for (int i = n - 1; i >= 0; i--) {
            K += sets[i].p;
            max = Math.max(max, K * sets[i].s);
        }
        return max;
    }
}

class Set implements Comparable<Set> {
    int p;
    int s;

    public Set(int p, int s) {
        super();
        this.p = p;
        this.s = s;
    }

    @Override
    public int compareTo(Set s) {
        if (this.s == s.s)
            return 0;
        return this.s > s.s ? 1 : -1;
    }
}