package srm318;

public class ReturnToHome {
    public double goHome(int X, int Y, int D, int T) {
        double dis = Math.sqrt(X * X + Y * Y);
        double min = dis;
        int n = (int) (dis / D);
        if (n > 0) {
            int jd = n * D; // jumped distance
            int jt = n * T; // jumped time
            min = Math.min(min, jt + dis - jd); // walk remaining
            // jump remaining
            if (jt + T < min && jd <= D + dis && D <= jd + dis && dis <= jd + D)
                min = jt + T;
        }
        // jump over, walk back
        n++;
        int jd = n * D;
        int jt = n * T;
        min = Math.min(min, jt + jd - dis);
        // jump over, jump back
        if (jt + T < min && jd <= D + dis && D <= jd + dis && dis <= jd + D)
            min = jt + T;
        return min;
    }
}
