public class QuantumAlchemy {
    private int[] amount = new int[256];
    private int[] need = new int[256];
    private String[] reacts;

    public int minSteps(String initial, String[] reactions) {
        reacts = reactions;
        for (int i = 0; i < initial.length(); i++)
            amount[initial.charAt(i)]++;
        return dfs('X', 1, 0);
    }

    private int dfs(char c, int a, int count) {
        if (amount[c] >= a) {
            amount[c] -= a;
            return count;
        }
        int n = a - amount[c];
        amount[c] = 0;
        if (need[c] > 1)
            return -1;
        int res = 0;
        boolean found = false;
        for (int i = 0; i < reacts.length; i++)
            if (reacts[i].charAt(reacts[i].length() - 1) == c) {
                found = true;
                for (int j = 0; j < reacts[i].length() - 3; j++) {
                    need[reacts[i].charAt(j)]++;
                    int s = dfs(reacts[i].charAt(j), n, 0);
                    need[reacts[i].charAt(j)]--;
                    if (s == -1)
                        return -1;
                    res += s;
                }
                break;
            }
        if (!found)
            return -1;
        return res * n + n + count;
    }
}
