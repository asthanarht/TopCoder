package srm555;

public class XorBoard {
    public final long MOD = 555555555;
    public long[][] C = new long[2500][2500];

    public int count(int H, int W, int Rcount, int Ccount, int S) {
        prepare();
        long result = 0;
        for (int r = Rcount % 2; r <= Math.min(H, Rcount); r += 2)
            for (int c = Ccount % 2; c <= Math.min(W, Ccount); c += 2)
                if ((H - r) * c + (W - c) * r == S) {
                    int rr = (Rcount - r) / 2, cc = (Ccount - c) / 2;
                    result += C[H][r] * C[W][c] % MOD * C[H + rr - 1][H - 1]
                            % MOD * C[W + cc - 1][W - 1] % MOD;
                }
        return (int) (result % MOD);
    }

    public void prepare() {
        for (int i = 0; i < 2500; i++) {
            C[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
                if (C[i][j] >= MOD)
                    C[i][j] -= MOD;
            }
        }
    }
}
