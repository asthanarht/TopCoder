public class IndicatorMotionReverse {
    private char s[] = { '|', '/', '-', 'N' };

    public String getMinProgram(String[] actions) {
        String action = "";
        for (int i = 0; i < actions.length; i++)
            action += actions[i];

        char as[] = action.toCharArray();
        if (as.length == 1)
            return "";
        char ls[] = new char[as.length - 1];
        for (int i = 0; i < ls.length; i++)
            ls[i] = getLetter(as[i], as[i + 1]);

        String result = "";
        char cur = ls[0];
        int count = 1;
        for (int i = 1; i < ls.length; i++)
            if (ls[i] == cur)
                count++;
            else {
                String segment = "";
                while (count > 99) {
                    segment += cur + "99";
                    count -= 99;
                }
                if (count > 0)
                    result += String.format("%c%2d%s", cur, count, segment);
                cur = ls[i];
                count = 1;
            }
        String segment = "";
        while (count > 99) {
            segment += cur + "99";
            count -= 99;
        }
        if (count > 0)
            result += String.format("%c%2d%s", cur, count, segment);
        if (result.length() > 100)
            result = result.substring(0, 97) + "...";
        result = result.replaceAll(" ", "0");
        return result;
    }

    private char getLetter(char s1, char s2) {
        int i1 = 0;
        int i2 = 0;
        for (; i1 < 4; i1++)
            if (s1 == s[i1])
                break;
        for (; i2 < 4; i2++)
            if (s2 == s[i2])
                break;
        if (i1 == i2)
            return 'S';
        if (i1 - i2 == 2 || i2 - i1 == 2)
            return 'F';
        if (i1 + 1 == i2 || i1 - i2 == 3)
            return 'R';
        return 'L';
    }
}
