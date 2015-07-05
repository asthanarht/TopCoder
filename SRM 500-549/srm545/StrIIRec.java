public class StrIIRec {
    public String recovstr(int n, int minInv, String minStr) {
        String res = "";
        for (int i = 0; i < n; i++) {
            for (char ch = 'a'; ch < (char) ('a' + n); ch++)
                if (res.indexOf(ch) == -1
                        && (res + ch).compareTo((i < minStr.length() ? minStr
                                .substring(0, i + 1) : minStr)) >= 0) {
                    String max = res + ch;
                    for (char cha = (char) ('a' + n - 1); cha >= 'a'; cha--)
                        if (res.indexOf(cha) == -1 && ch != cha)
                            max += cha;
                    if (count(max) >= minInv) {
                        res += ch;
                        break;
                    }
                }
        }
        return res;
    }

    private int count(String s) {
        int count = 0;
        for (int i = 1; i < s.length(); i++)
            for (int j = 0; j < i; j++)
                if (s.charAt(j) > s.charAt(i))
                    count++;
        return count;
    }
}
