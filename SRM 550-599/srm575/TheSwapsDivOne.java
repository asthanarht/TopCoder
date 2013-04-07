package srm575;

public class TheSwapsDivOne {
    public double find(String[] sequence, int k) {
        String seq = "";
        for (int i = 0; i < sequence.length; i++)
            seq += sequence[i];
        int n = seq.length();

        int[] num = new int[n];
        for (int i = 0; i < n; i++)
            num[i] = seq.charAt(i) - '0';

        double caseNum = c2(n);
        double stayCase = c2(n - 1);

        // possibility that a num stays in place or not after k swaps
        double pSame = 1;
        double pOther = 0;
        for (int turn = 0; turn < k; turn++) {
            pSame = (pSame * stayCase + pOther) / caseNum;
            pOther = 1 - pSame;
        }
        double pAnother = pOther / (n - 1);

        // expected number at each position in the final sequence
        double[] numExpect = new double[n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (i == j)
                    numExpect[j] += num[i] * pSame;
                else
                    numExpect[j] += num[i] * pAnother;

        // how many subsequences each position appears in
        int[] outCase = new int[n];
        for (int i = 0; i < n; i++)
            outCase[i] = (i + 1) * (n - i);
        int totalCase = n * (n + 1) / 2;

        double expect = 0;
        for (int i = 0; i < n; i++)
            expect += numExpect[i] * outCase[i];
        expect /= totalCase;

        return expect;
    }

    // select 2 from n
    private double c2(int n) {
        return n * (n - 1) / 2;
    }
}
