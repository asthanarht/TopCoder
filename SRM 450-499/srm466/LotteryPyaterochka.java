package srm466;

import java.math.BigInteger;

public class LotteryPyaterochka {
    public double chanceToWin(int N) {
        if (N < 3)
            return 1;
        BigInteger[][] C = new BigInteger[512][512];
        for (int i = 0; i < 512; i++)
            for (int j = 0; j < 512; j++)
                C[i][j] = BigInteger.ZERO;
        for (int i = 0; i < 512; i++)
            C[i][0] = BigInteger.ONE;
        for (int i = 1; i < 512; i++)
            for (int j = 1; j <= i; j++)
                C[i][j] = C[i - 1][j - 1].add(C[i - 1][j]);
        BigInteger get3 = C[5][3].multiply(C[5 * (N - 1)][2]).multiply(
                new BigInteger("" + N));
        BigInteger get4 = C[5][4].multiply(C[5 * (N - 1)][1]).multiply(
                new BigInteger("" + N));
        BigInteger get5 = C[5][5].multiply(new BigInteger("" + N));
        BigInteger win = get3.add(get4).add(get5);
        BigInteger total = C[5 * N][5];
        return win.doubleValue() / total.doubleValue();
    }
}
