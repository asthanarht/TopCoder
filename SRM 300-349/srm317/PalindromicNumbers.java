package srm317;

public class PalindromicNumbers {
    private int L;
    private int U;

    public int countPalNums(int lower, int upper) {
        L = lower;
        U = upper;
        int count = 0;
        for (int i = 0; i < 10; i++) {
            count += dfs("" + i);
            count += dfs(i + "" + i);
        }
        return count;
    }

    private int dfs(String num) {
        int count = 0;
        if (num.charAt(0) != '0') {
            int n = Integer.parseInt(num);
            if (n >= L && n <= U)
                count++;
            if (n > U)
                return count;
        }
        if (num.length() > 7)
            return count;
        for (int i = 0; i < 10; i++)
            count += dfs(i + num + i);
        return count;
    }
}
