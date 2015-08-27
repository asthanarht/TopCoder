public class GoodString {

    public String isGood(String s) {
        int cnt = 0;
        for (int i = 0; i < s.length(); i++)
            if (s.charAt(i) == 'a')
                cnt++;
            else {
                cnt--;
                if (cnt < 0)
                    return "Bad";
            }
        return cnt == 0 ? "Good" : "Bad";
    }

}
