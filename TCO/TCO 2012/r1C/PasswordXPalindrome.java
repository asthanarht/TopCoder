public class PasswordXPalindrome {
    private int n;

    public int minSwaps(String password) {
        n = password.length();
        int[] check = new int[26];
        for (int i = 0; i < n; i++)
            check[password.charAt(i) - 'a']++;
        int count = 0;
        for (int i = 0; i < 26; i++)
            if (check[i] % 2 == 1)
                count++;
        if (count > 1)
            return -1;
        boolean change = false;
        if (n % 2 == 1)
            for (int index = 0; index < n; index++)
                if (check[password.charAt(index) - 'a'] % 2 == 1
                        && index != n / 2) {
                    password = swap(password, index, n / 2);
                    change = true;
                }
        String s1 = password, s2 = "";
        for (int i = change ? 1 : 0;; i++) {
            if (check(s1))
                return i;
            for (int j = 0; j < n - 1; j++)
                for (int k = j + 1; k < n; k++)
                    if (s1.charAt(j) == s1.charAt(k) && n - 1 - k != j)
                        s2 = swap(s1, j, n - 1 - k);
            s1 = s2;
        }
    }

    private boolean check(String s) {
        for (int i = 0, j = n - 1; i < j; i++, j--)
            if (s.charAt(i) != s.charAt(j))
                return false;
        return true;
    }

    private String swap(String s, int j, int k) {
        char[] array = s.toCharArray();
        char hold = array[j];
        array[j] = array[k];
        array[k] = hold;
        return String.valueOf(array);
    }
}