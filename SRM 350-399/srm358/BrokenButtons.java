package srm358;

public class BrokenButtons {
    public int minPresses(int page, int[] broken) {
        boolean[] bad = new boolean[10];
        for (int i = 0; i < broken.length; i++)
            bad[broken[i]] = true;
        int res = Math.abs(page - 100);
        for (int i = 0; i <= 1000000; i++) {
            boolean ok = true;
            String s = Integer.toString(i);
            for (int j = 0; j < s.length(); j++)
                if (bad[s.charAt(j) - '0'])
                    ok = false;
            if (ok)
                res = Math.min(res, s.length() + Math.abs(i - page));
        }
        return res;
    }
}
