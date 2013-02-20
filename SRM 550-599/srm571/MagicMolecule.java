package srm571;

public class MagicMolecule {
    private static int n;
    private static long[] mask;
    private static boolean[][] graph;
    private static int result = -1;
    private static int[] mPower;

    public static int maxMagicPower(int[] magicPower, String[] magicBond) {
        n = magicPower.length;
        mPower = magicPower;
        mask = new long[n];
        graph = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                if (magicBond[i].charAt(j) == 'Y' || i == j) {
                    graph[i][j] = true;
                    mask[i] |= 1L << j;
                }
        }
        go(0, n, (1L << n) - 1);
        return result;
    }

    /**
     * 
     * @param iBond
     *            the ith bond (view graph as 1d array
     * @param m
     * @param mask
     *            chosen atoms
     */
    private static void go(int iBond, int m, long mask) {
        if (m * 3 < n * 2)
            return;
        // after examination of the last bond
        if (iBond == n * n) {
            int power = 0;
            for (int i = 0; i < n; i++)
                if ((mask & (1L << i)) > 0) {
                    // if not all m atoms are connected
                    // mask shall be a subset of connected atoms of the ith atom
                    if ((MagicMolecule.mask[i] & mask) != mask)
                        return;
                    power += mPower[i];
                }
            result = Math.max(result, power);
            return;
        }
        // the two atoms of the iBond bond
        int atom1 = iBond % n, atom2 = iBond / n;
        // if they are connected or any of them are not chosen
        if (graph[atom1][atom2] || (mask & (1L << atom1)) == 0
                || (mask & (1L << atom2)) == 0)
            go(iBond + 1, m, mask); // keep both chosen
        else {
            // if two atoms are not connected,
            // then at least one of them shall not be chosen
            go(iBond + 1, m - 1, mask & ~(1L << atom1));
            go(iBond + 1, m - 1, mask & ~(1L << atom2));
        }
    }
}
