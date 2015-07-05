public class PastingPaintingDivOne {
    public long[] countColors(String[] clipboard, int T) {
        long[] count = new long[4];
        int r = clipboard.length, c = clipboard[0].length();
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++) {
                char ch = clipboard[i].charAt(j);
                count["RGB.".indexOf(ch)] += ((i == 0 || j == 0) ? T : 1);
                if (ch == '.')
                    for (int move = 1; move < T && i + move < r && j + move < c; move++) {
                        ch = clipboard[i + move].charAt(j + move);
                        if (ch != '.') {
                            count["RGB.".indexOf(ch)] += 
                                    ((i == 0 || j == 0) ? T - move : 1);
                            break;
                        }
                    }
            }
        return new long[] { count[0], count[1], count[2] };
    }
}
