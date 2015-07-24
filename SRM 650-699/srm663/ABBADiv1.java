public class ABBADiv1 {

    public String canObtain(String initial, String target) {
        if (dfs(initial, target))
            return "Possible";
        return "Impossible";
    }

    private boolean dfs(String s, String t) {
        if (t.compareTo(s) == 0)
            return true;
        if (t.length() == s.length())
            return false;
        int end = t.length() - 1;
        if (t.charAt(end) == 'A')
            if (dfs(s, t.substring(0, end)))
                return true;
        if (t.charAt(0) == 'B') {
            String t0 = "";
            for (int i = end; i > 0; i--)
                t0 += t.charAt(i);
            if (dfs(s, t0))
                return true;
        }
        return false;
    }

}
