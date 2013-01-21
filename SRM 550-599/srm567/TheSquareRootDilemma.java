package srm567;

public class TheSquareRootDilemma {
    public int countPairs(int N, int M) {
        int count = ((int) Math.sqrt(N)) * ((int) Math.sqrt(M));
        boolean[] tick = new boolean[N + 1];
        for (int i = 2; i <= N; i++) {
            if (tick[i])
                continue;
            int sqrti = (int) Math.sqrt(i);
            if (sqrti * sqrti != i) {
                for (int a = 1; a * a * i <= N; a++)
                    tick[a * a * i] = true;
                count += ((int) Math.sqrt(N / i)) * ((int) Math.sqrt(M / i));
            }
        }
        return count;
    }
}
