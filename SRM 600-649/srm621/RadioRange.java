package srm621;

import java.util.Arrays;

public class RadioRange {

    public double RadiusProbability(int[] X, int[] Y, int[] R, int Z) {
        int n = X.length;
        Segment[] segs = new Segment[n];
        for (int i = 0; i < n; i++) {
            double d = Math.sqrt(X[i] * (double) X[i] + Y[i] * (double) Y[i]);
            if (d <= R[i])
                segs[i] = new Segment(0, d + R[i]);
            else
                segs[i] = new Segment(d - R[i], d + R[i]);
        }
        Arrays.sort(segs);

        double len = 0;
        double from = -1, to = -1;
        for (int i = 0; i < n; i++) {
            if (segs[i].min > to) {
                if (to <= Z)
                    len += to - from;
                else if (Z > from)
                    len += Z - from;
                from = segs[i].min;
                to = segs[i].max;
            }
            else {
                to = Math.max(to, segs[i].max);
            }
        }
        if (to <= Z)
            len += to - from;
        else if (Z > from)
            len += Z - from;

        return (Z - len) / Z;
    }

}

class Segment implements Comparable<Segment> {
    double min;
    double max;

    public Segment(double min, double max) {
        super();
        this.min = min;
        this.max = max;
    }

    public int compareTo(Segment s) {
        return Double.compare(min, s.min);
    }

}
