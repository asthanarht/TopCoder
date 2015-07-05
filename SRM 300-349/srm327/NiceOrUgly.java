public class NiceOrUgly {
    private String text;

    public String describe(String s) {
        text = s;
        int count3 = 0, count5 = 0, countq = 0;
        for (int i = 0; i < s.length(); i++)
            if (isVowel(i)) {
                count3++;
                if (count3 >= 3)
                    return "UGLY";
                count5 = 0;
            }
            else if (isConsonant(i)) {
                count5++;
                if (count5 >= 5)
                    return "UGLY";
                count3 = 0;
            }
            else {
                countq++;
                count3 = 0;
                count5 = 0;
            }
        if (countq == 0)
            return "NICE";
        boolean c3, c5;
        for (int i = 0; i < s.length(); i++)
            if (s.charAt(i) == '?') {
                c3 = false;
                c5 = false;
                boolean check = true;
                for (int j = 1; j < 3; j++)
                    if (!isVowel(i - j))
                        check = false;
                if (check)
                    c3 = true;
                check = true;
                for (int j = 1; j < 3; j++)
                    if (!isVowel(i + j))
                        check = false;
                if (check)
                    c3 = true;
                check = true;
                for (int j = 1; j < 5; j++)
                    if (!isConsonant(i - j))
                        check = false;
                if (check)
                    c5 = true;
                check = true;
                for (int j = 1; j < 5; j++)
                    if (!isConsonant(i + j))
                        check = false;
                if (check)
                    c5 = true;
                if (c3 && c5)
                    return "UGLY";
            }
        count3 = 0;
        count5 = 0;
        for (int i = 0; i < s.length(); i++)
            if (isVowel(i)) {
                count3++;
                if (count3 >= 3)
                    return "42";
                count5 = 0;
            }
            else if (isConsonant(i)) {
                count5++;
                if (count5 >= 5)
                    return "42";
                count3 = 0;
            }
            else {
                count3++;
                if (count3 >= 3)
                    return "42";
                count5++;
                if (count5 >= 5)
                    return "42";
            }
        return "NICE";
    }

    private boolean isVowel(int index) {
        if (index < 0 || index >= text.length())
            return false;
        char c = text.charAt(index);
        if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U')
            return true;
        return false;
    }

    private boolean isConsonant(int index) {
        if (index < 0 || index >= text.length())
            return false;
        char c = text.charAt(index);
        if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U'
                || c == '?')
            return false;
        return true;
    }
}
