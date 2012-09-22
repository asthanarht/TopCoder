package srm303;

public class Segments {
    public String intersection(int[] s1, int[] s2) {
        int count = 0;
        for (int i = -1000; i <= 1000; i++)
            for (int j = -1000; j <= 1000; j++)
                if (check(s1, i, j) && check(s2, i, j))
                    count++;
        if (count == 0)
            return "NO";
        if (count == 1)
            return "POINT";
        return "SEGMENT";
    }

    private boolean check(int[] s, int i, int j) {
        if (i == s[0] && i == s[2] && j >= Math.min(s[1], s[3])
                && j <= Math.max(s[1], s[3]))
            return true;
        if (j == s[1] && j == s[3] && i >= Math.min(s[0], s[2])
                && i <= Math.max(s[0], s[2]))
            return true;
        return false;
    }
}
