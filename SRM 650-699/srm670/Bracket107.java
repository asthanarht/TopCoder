import java.util.HashSet;

public class Bracket107 {

    public int yetanother(String s) {
        for (int i = 0; i < s.length(); i++)
            for (int j = 0; j < s.length(); j++) {
                StringBuffer sb = new StringBuffer(s);
                char c = sb.charAt(i);
                sb.deleteCharAt(i);
                sb.insert(j, c);
                String str = sb.toString();
                if (valid(str))
                    set.add(str);
            }
        return set.size() - 1;
    }

    HashSet<String> set = new HashSet<String>();

    private boolean valid(String s) {
        int cnt = 0;
        for (int i = 0; i < s.length(); i++)
            if (s.charAt(i) == '(')
                cnt++;
            else {
                cnt--;
                if (cnt < 0)
                    return false;
            }
        return cnt == 0;
    }
}
