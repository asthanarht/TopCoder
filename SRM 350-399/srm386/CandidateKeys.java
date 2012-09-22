package srm386;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;

public class CandidateKeys {
    public int[] getKeys(String[] table) {
        int m = table.length;
        int n = table[0].length();
        char[][] grid = new char[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = table[i].charAt(j);
        BigInteger mask = BigInteger.ZERO;
        HashSet<String> set = null;
        HashSet<BigInteger> candidates = new HashSet<BigInteger>();
        ArrayList<BigInteger> invalid = null;
        for (int loop = 1; loop < 1 << n; loop++) {
            mask = mask.add(BigInteger.ONE);
            set = new HashSet<String>();
            for (int i = 0; i < m; i++) {
                String ident = "";
                for (int j = 0; j < n; j++)
                    if (mask.testBit(j))
                        ident += grid[i][j];
                set.add(ident);
            }
            if (set.size() == m) {
                boolean ok = true;
                invalid = new ArrayList<BigInteger>();
                for (BigInteger candidate : candidates) {
                    int count1 = 0;
                    int count2 = 0;
                    for (int bit = 0; bit < n; bit++)
                        if (candidate.testBit(bit) && !mask.testBit(bit))
                            count1++;
                        else if (!candidate.testBit(bit) && mask.testBit(bit))
                            count2++;
                    if (count1 > 0 && count2 == 0)
                        invalid.add(candidate);
                    else if (count1 == 0 && count2 > 0)
                        ok = false;
                }
                for (BigInteger bad : invalid)
                    candidates.remove(bad);
                if (ok)
                    candidates.add(mask);
            }
        }
        int[] result = {};
        if (candidates.size() > 0) {
            result = null;
            for (BigInteger candidate : candidates) {
                if (result == null) {
                    result = new int[2];
                    result[0] = candidate.bitCount();
                    result[1] = result[0];
                }
                else {
                    int count = candidate.bitCount();
                    result[0] = Math.min(result[0], count);
                    result[1] = Math.max(result[1], count);
                }
            }
        }
        return result;
    }
}
