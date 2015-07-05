public class ThePalindrome {
    public int find(String s) {
        int result = s.length() * 2 - 1;
        for (int i = s.length() / 2; i < s.length(); i++) {
            boolean ok = false;
            if (s.length() % 2 == 0 || i > s.length() / 2) {
                for (int j = i - 1, k = i; j >= 0 && k < s.length(); j--, k++)
                    if (s.charAt(j) != s.charAt(k)) {
                        ok = false;
                        break;
                    }
                    else
                        ok = true;
                if (ok)
                    result = Math.min(result, i * 2);
            }
            for (int j = i - 1, k = i + 1; j >= 0 && k < s.length(); j--, k++)
                if (s.charAt(j) != s.charAt(k)) {
                    ok = false;
                    break;
                }
                else
                    ok = true;
            if (ok)
                result = Math.min(result, i * 2 + 1);
        }
        return result;
    }
}
