public class NewArenaPassword {
    public int minChange(String oldPassword, int K) {
        int n = oldPassword.length();
        if (K == n)
            return 0;
        char[] string = oldPassword.toCharArray();
        int result = 0;
        boolean[] check = new boolean[n];
        for (int i = 0; i < K; i++)
            if (!check[i]) {
                int num = 0;
                int[] count = new int[26];
                int index = i, match = index + n - K;
                while (index < K) {
                    count[string[index] - 'a']++;
                    check[index] = true;
                    num++;
                    index = match;
                    match = index + n - K;
                }
                count[string[index] - 'a']++;
                check[index] = true;
                num++;
                int max = 0;
                for (int j = 0; j < 26; j++)
                    max = Math.max(max, count[j]);
                result += num - max;
            }
        return result;
    }
}