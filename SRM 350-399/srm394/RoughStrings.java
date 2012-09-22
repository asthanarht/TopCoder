package srm394;

import java.util.Arrays;

public class RoughStrings {
    public int minRoughness(String s, int n) {
        int[] counts = new int[26];
        for (int i = 0; i < s.length(); i++)
            counts[s.charAt(i) - 'a']++;
        Arrays.sort(counts);
        int res = Integer.MAX_VALUE;
        for (int min = 0; min <= counts[25]; min++)
            for (int max = min; max <= counts[25]; max++) {
                int count = 0;
                for (int i = 0; i < 26; i++)
                    if (counts[i] > max)
                        count += counts[i] - max;
                    else if (counts[i] < min)
                        count += counts[i];
                if (count <= n)
                    res = Math.min(res, max - min);
            }
        return res;
    }
}
