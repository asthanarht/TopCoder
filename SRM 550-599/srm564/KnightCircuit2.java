package srm564;

public class KnightCircuit2 {
    public int maxSize(int w, int h) {
        if (w == 1 || h == 1)
            return 1;
        if (w == 2 || h == 2)
            return (Math.max(w, h) + 1) / 2;
        if (w == 3 && h == 3)
            return 8;
        return w * h;
    }
}
