public class GooseTattarrattatDiv1 {
    public int getmin(String S) {
        int[] cnt = new int[26];
        for (int i = 0; i < S.length(); i++)
            cnt[S.charAt(i) - 'a']++;

        boolean[][] same = new boolean[26][26];
        for (int left = 0, right = S.length() - 1; left < right; left++, right--) {
            int cleft = S.charAt(left) - 'a';
            int cright = S.charAt(right) - 'a';
            same[cleft][cright] = same[cright][cleft] = true;
        }
        for (int c = 0; c < 26; c++)
            same[c][c] = true;

        for (int k = 0; k < 26; k++)
            for (int i = 0; i < 26; i++)
                for (int j = 0; j < 26; j++)
                    same[i][j] |= same[i][k] && same[k][j];

        int res = 0;
        for (int c1 = 0; c1 < 26; c1++)
            if (cnt[c1] > 0) {
                int max = 0;
                for (int c2 = 0; c2 < 26; c2++)
                    if (same[c1][c2]) {
                        res += cnt[c2];
                        max = Math.max(max, cnt[c2]);
                        cnt[c2] = 0;
                    }
                res -= max;
            }
        return res;
    }
}
