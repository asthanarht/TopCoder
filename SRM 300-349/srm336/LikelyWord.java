public class LikelyWord {
    private static long maxRange = 0;
    private static int index = 0, count = 0;

    public static int likely(String[] dictionary, int k) {
        int n = dictionary.length;
        for (int i = 0; i < n - 1; i++) {
            String s1 = dictionary[i];
            String s2 = dictionary[i + 1];
            long range = p(s2, k);
            if (s2.length() > k)
                range++;
            range -= p(s1, k);
            if (s1.length() >= k)
                range--;
            check(range, i + 1);
        }
        {
            long range = p(dictionary[0], k);
            if (dictionary[0].length() > k)
                range++;
            check(range, 0);
        }
        {
            String s = "";
            for (int i = 0; i < k; i++)
                s += 'z';
            long range = p(s, k);
            range++;
            range -= p(dictionary[n - 1], k);
            if (dictionary[n - 1].length() >= k)
                range--;
            check(range, n);
        }
        if (count == 1)
            return index;
        return -1;
    }

    private static void check(long range, int i) {
        if (range > maxRange) {
            maxRange = range;
            index = i;
            count = 1;
        }
        else if (range == maxRange)
            count++;
    }

    private static long p(String word, int k) {
        int len = word.length();
        long p = 0;
        for (int i = 0; i < Math.min(len, k); i++) {
            long pp = word.charAt(i) - 'a';
            for (int j = i + 1; j < k; j++)
                pp *= 26;
            p += pp;
        }
        return p;
    }
}
