package srm351;

public class CoinsExchange {
    public int countExchanges(int G1, int S1, int B1, int G2, int S2, int B2) {
        int count = 0;
        if (B1 < B2) {
            int n = (B2 - B1 + 8) / 9;
            count += n;
            S1 -= n;
            B1 += 9 * n;
        }
        if (S1 < S2 && G1 > G2) {
            int n = Math.min(G1 - G2, (S2 - S1 + 8) / 9);
            count += n;
            G1 -= n;
            S1 += 9 * n;
        }
        if (S1 < S2 && B1 > B2) {
            int n = Math.min(S2 - S1, (B1 - B2) / 11);
            count += n;
            S1 += n;
            B1 -= 11 * n;
        }
        if (G1 < G2 && S1 > S2) {
            int n = Math.min(G2 - G1, (S1 - S2) / 11);
            count += n;
            G1 += n;
            S1 -= 11 * n;
        }
        if (G1 < G2 && B1 > B2) {
            int n = Math.min(G2 - G1, ((S1 - S2) * 11 + B1 - B2) / 121);
            if (n > 0) {
                count += n * 12 - (S1 - S2);
                G1 += n;
                B1 -= 121 * n - (S1 - S2) * 11;
                S1 = S2;
            }
        }
        if (G1 >= G2 && S1 >= S2 && B1 >= B2)
            return count;
        return -1;
    }
}
