import java.util.ArrayList;

public class LongSeat {
    private long L;
    private int n;
    private int[] w;
    private int and = -1, or = 0;

    public String[] canSit(int L, int[] width) {
        this.L = L;
        w = width;
        n = width.length;
        // combination of sitting ones
        for (int mask = 0; mask < (1 << n); mask++) {
            // final gaps between them
            ArrayList<Segment> loc = new ArrayList<Segment>();
            for (int i = 0; i <= Integer.bitCount(mask); i++)
                loc.add(new Segment(0, 1000000000L));
            long sum = 0;
            for (int i = 0; i < n; i++)
                if ((mask & (1 << i)) > 0)
                    sum += w[i];
            if (sum > L)
                continue; // not all sitting ones can fit in
            // verify from the last one
            if (test(mask, loc, n - 1)) {
                and &= mask; // whether they always sit
                or |= mask; // whether they have chance to sit in some cases
            }
        }
        String[] res = new String[n];
        for (int i = 0; i < n; i++) {
            if ((and & (1 << i)) > 0)
                res[i] = "Sit";
            else if ((or & (1 << i)) > 0)
                res[i] = "Unsure";
            else
                res[i] = "Stand";
        }
        return res;
    }

    private boolean test(int mask, ArrayList<Segment> loc, int i) {
        for (Segment seg : loc)
            if (seg.w >= seg.d) // inserted too much in one gap
                return false;
        if (i == -1)
            return loc.get(0).d > L; // not all standing ones can stand valid

        // sit
        if ((mask & (1 << i)) > 0) {
            // original one and the gap to its right:
            // (1) (p.w + q.w) + (p.gap + q.gap) OR
            // (2) (q.w + p.w) + (q.gap + p.gap)
            // for (1) - after shifting q to its position:
            // (p.w + p.gap) + (q.w + q.gap)
            // for (2) - after shifting p to its position:
            // (q.w + q.gap) + (p.w + p.gap)
            // note that for both (1) and (2), the original ones are the same
            // which means, the order of p and q do not matter
            for (int p = 0; p < loc.size(); p++)
                for (int q = 0; q < p; q++) {
                    ArrayList<Segment> nloc = new ArrayList<Segment>();
                    Segment nseg = new Segment(loc.get(p).w + loc.get(q).w
                            + w[i], loc.get(p).d + loc.get(q).d + w[i]);
                    nloc.add(nseg);
                    for (int k = 0; k < loc.size(); k++)
                        if (k != p && k != q)
                            nloc.add(loc.get(k));
                    if (test(mask, nloc, i - 1))
                        return true;
                }
        }
        // stand
        else {
            // for the ith one to stand
            // all the existing gaps should not exceed its width
            for (Segment seg : loc)
                seg.d = Math.min(seg.d, w[i]);
            return test(mask, loc, i - 1);
        }

        return false;
    }

}

class Segment {
    long w;
    long d;

    public Segment(long w, long d) {
        super();
        this.w = w;
        this.d = d;
    }

    @Override
    public String toString() {
        return "Segment [" + w + ", " + d + "]";
    }
}