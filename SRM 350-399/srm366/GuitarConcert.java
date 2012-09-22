package srm366;

import java.math.BigInteger;
import java.util.Arrays;

public class GuitarConcert {
    public static String[] buyGuitars(String[] guitarNames, String[] guitarSongs) {
        int n = guitarNames.length, m = guitarSongs[0].length();
        BigInteger[] gs = new BigInteger[n];
        for (int i = 0; i < n; i++) {
            BigInteger gsi = BigInteger.ZERO;
            for (int j = 0; j < m; j++) {
                gsi = gsi.shiftLeft(1);
                if (guitarSongs[i].charAt(j) == 'Y')
                    gsi = gsi.add(BigInteger.ONE);
            }
            gs[i] = gsi;
        }
        int max = 0; // song
        int min = n; // guitar
        BigInteger best = null; // result combination
        BigInteger combination = BigInteger.ZERO, check = null;
        for (int i = 1; i < 1 << n; i++) {
            combination = combination.add(BigInteger.ONE);
            check = BigInteger.ZERO;
            for (int j = 0; j < n; j++)
                if (combination.testBit(j))
                    check = check.or(gs[j]);
            if (check.bitCount() > max) {
                max = check.bitCount();
                min = combination.bitCount();
                best = combination;
            }
            else if (check.bitCount() == max) {
                if (combination.bitCount() < min) {
                    min = combination.bitCount();
                    best = combination;
                }
                else if (combination.bitCount() == min) {
                    if (best == null || compare(guitarNames, best, combination)) {
                        min = combination.bitCount();
                        best = combination;
                    }
                }
            }
        }
        return max == 0 ? new String[0] : translate(guitarNames, best);
    }

    private static boolean compare(String[] gs, BigInteger c1, BigInteger c2) {
        String[] s1 = translate(gs, c1), s2 = translate(gs, c2);
        for (int i = 0; i < s1.length; i++)
            if (s1[i].compareTo(s2[i]) > 0)
                return true;
            else if (s1[i].compareTo(s2[i]) < 0)
                return false;
        return false;
    }

    private static String[] translate(String[] gs, BigInteger c) {
        String[] s = new String[c.bitCount()];
        for (int i = 0, cur = 0; i < gs.length; i++)
            if (c.testBit(i))
                s[cur++] = gs[i];
        Arrays.sort(s);
        return s;
    }
}
