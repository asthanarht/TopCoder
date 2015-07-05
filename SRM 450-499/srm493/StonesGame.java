public class StonesGame {
    public String winner(int N, int M, int K, int L) {
        if (check(N, M, K, L))
            return "Romeo";
        boolean found = false, ok = true;
        for (int loc = 1; loc <= N; loc++)
            if (loc != L && check(N, M, K, loc))
                if (check(N, loc, K, L))
                    found = true;
                else
                    ok = false;
        if (found && ok)
            return "Strangelet";
        return "Draw";
    }

    boolean check(int N, int M, int K, int L) {
        int dis = Math.abs(M - L) + 1;
        if (dis % 2 != K % 2)
            return false;
        if (K < dis)
            return false;
        int extra = (K - dis) / 2;
        if (extra >= Math.min(M, L) || extra > N - Math.max(M, L))
            return false;
        return true;
    }
}
