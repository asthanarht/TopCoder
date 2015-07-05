public class AmoebaCode {
    int n, k, mindis;
    int[] comb;
    String CODE;

    public int find(String code, int K) {
        CODE = code;
        String sub = "";
        for (int i = 0; i < K; i++)
            sub += "0";
        while (CODE.startsWith(sub))
            CODE = CODE.substring(sub.length());
        while (CODE.endsWith(sub))
            CODE = CODE.substring(0, CODE.length() - sub.length());
        n = CODE.length();
        comb = new int[n];
        init();
        k = K;
        for (mindis = K; mindis > 1; mindis--) {
            if (!check())
                continue;
            if (test(0))
                return mindis;
        }
        return 1;
    }

    private boolean check() {
        for (int i = 0; i < n; i++)
            if (comb[i] > 0)
                for (int j = i + 1, count = 1; j < n && count < mindis; j++, count++)
                    if (comb[i] == comb[j])
                        return false;
        return true;
    }

    private boolean test(int pos) {
        if (pos == n)
            return true;
        if (comb[pos] == 0) {
            next: for (int digit = 1; digit <= k; digit++) {
                comb[pos] = digit;
                for (int i = pos - 1, count = 1; i >= 0 && count < mindis; i--, count++)
                    if (comb[i] == comb[pos])
                        continue next;
                if (test(pos + 1))
                    return true;
            }
            comb[pos] = 0;
            return false;
        }
        else {
            for (int i = pos - 1, count = 1; i >= 0 && count < mindis; i--, count++)
                if (comb[i] == comb[pos])
                    return false;
            return test(pos + 1);
        }
    }

    private void init() {
        for (int i = 0; i < n; i++)
            comb[i] = CODE.charAt(i) - '0';
    }
}
