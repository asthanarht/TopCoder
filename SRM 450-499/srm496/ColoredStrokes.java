public class ColoredStrokes {
    public int getLeast(String[] picture) {
        int result = 0;
        // count red strokes
        for (int i = 0; i < picture.length; i++) {
            char pre = picture[i].charAt(0);
            if (pre == 'R' || pre == 'G')
                result++;
            for (int j = 1; j < picture[i].length(); j++) {
                char c = picture[i].charAt(j);
                if (c != pre) {
                    // count only at the beginning of each stroke
                    if ((pre == 'B' || pre == '.') && (c == 'R' || c == 'G'))
                        result++;
                    pre = c;
                }
            }
        }
        // count blue strokes
        for (int j = 0; j < picture[0].length(); j++) {
            char pre = picture[0].charAt(j);
            if (pre == 'B' || pre == 'G')
                result++;
            for (int i = 1; i < picture.length; i++) {
                char c = picture[i].charAt(j);
                if (c != pre) {
                    if ((pre == 'R' || pre == '.') && (c == 'B' || c == 'G'))
                        result++;
                    pre = c;
                }
            }
        }
        return result;
    }
}
