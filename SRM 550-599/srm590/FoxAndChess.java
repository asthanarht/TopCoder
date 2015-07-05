public class FoxAndChess {
    public String ableToMove(String begin, String target) {
        boolean ok = true;
        int step = 1;
        do {
            int from = find(step, begin);
            if (from == -1)
                break;
            int to = find(step, target);
            if (to == -1) {
                ok = false;
                break;
            }
            if (begin.charAt(from) != target.charAt(to))
                ok = false;
            if (from < to && begin.charAt(from) == 'L')
                ok = false;
            if (from > to && begin.charAt(from) == 'R')
                ok = false;
            step++;
        } while (ok);
        if (find(step, target) != -1)
            ok = false;
        return ok ? "Possible" : "Impossible";
    }

    private int find(int n, String string) {
        for (int count = 0, i = 0; i < string.length(); i++)
            if (string.charAt(i) != '.') {
                count++;
                if (count == n)
                    return i;
            }
        return -1;
    }
}
