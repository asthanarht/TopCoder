package srm428;

public class TheLuckyString {
    private int total = 0;
    private int[] chars = new int[26];

    public int count(String s) {
        for (int i = 0; i < s.length(); i++)
            chars[s.charAt(i) - 'a']++;
        dfs(0, new int[s.length()], chars);
        return total;
    }

    private void dfs(int loc, int[] string, int[] cs) {
        if (loc == string.length) {
            total++;
        }
        else {
            for (int i = 0; i < cs.length; i++)
                if (cs[i] > 0) {
                    if (loc > 0 && i == string[loc - 1])
                        continue;
                    string[loc] = i;
                    cs[i]--;
                    dfs(loc + 1, string, cs);
                    cs[i]++;
                }
        }
    }
}
