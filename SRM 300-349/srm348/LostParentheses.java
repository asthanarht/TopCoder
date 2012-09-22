package srm348;

public class LostParentheses {
    public int minResult(String e) {
        String[] minus = e.split("-");
        int res = 0;
        for (int i = 0; i < minus.length; i++) {
            int num = 0;
            String[] plus = minus[i].split("\\+");
            for (int j = 0; j < plus.length; j++)
                num += Integer.parseInt(plus[j]);
            if (i == 0)
                res += num;
            else
                res -= num;
        }
        return res;
    }
}
