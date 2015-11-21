import java.util.Arrays;

public class BearCavalry {

    private int MOD = 1000000007;

    public int countAssignments(int[] warriors, int[] horses) {
        int res = 0;
        for (int i = 0; i < horses.length; i++) {
            res += go(warriors, horses, i);
            if (res >= MOD)
                res -= MOD;
        }
        return res;
    }

    private int go(int[] warriors, int[] horses, int h) {
        int max = warriors[0] * horses[h];
        int n = warriors.length - 1;
        int[] hs = new int[n];
        for (int i = 0; i < n; i++)
            hs[i] = i < h ? horses[i] : horses[i + 1];
        Arrays.sort(hs);
        int[] ws = new int[n];
        for (int i = 1; i <= n; i++) {
            int opt = 0;
            for (int j = 0; j < n; j++)
                if (warriors[i] * hs[j] < max)
                    opt = j + 1;
            if (opt == 0)
                return 0;
            ws[i - 1] = opt;
        }
        Arrays.sort(ws);
        long total = 1;
        for (int i = 0; i < n; i++) {
            int opt = ws[i] - i;
            if (opt <= 0)
                return 0;
            total *= opt;
            total %= MOD;
        }
        return (int) total;
    }
}
