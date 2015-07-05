public class TheJediTest {
    public int minimumSupervisors(int[] students, int K) {
        if (System.currentTimeMillis() % 2 == 0)
            return minimumSupervisors_mask(students, K);
        else
            return minimumSupervisors_greedy(students, K);
    }

    public int minimumSupervisors_mask(int[] students, int K) {
        int n = students.length;
        int result = Integer.MAX_VALUE;
        for (int mask = 0; mask < (1 << n - 1); mask++) {
            int[] student = students.clone();
            for (int i = 0; i < n - 1; i++)
                if (((1 << i) & mask) == 0) {
                    int extra = Math.min(students[i], student[i] % K);
                    student[i] -= extra;
                    student[i + 1] += extra;
                }
                else {
                    int move = Math.min(students[i + 1], K - student[i] % K);
                    student[i] += move;
                    student[i + 1] -= move;
                }
            int jedi = 0;
            for (int i = 0; i < n; i++)
                jedi += (student[i] + K - 1) / K;
            result = Math.min(result, jedi);
        }
        return result;
    }

    public static int minimumSupervisors_greedy(int[] students, int K) {
        int n = students.length;
        int[] fromDown = new int[n];
        for (int i = 0; i < n - 1; i++) {
            int extra = (students[i] + fromDown[i]) % K;
            if (students[i] >= extra) {
                fromDown[i + 1] += extra;
                students[i] -= extra;
                continue;
            }
            int need = K - (students[i] + fromDown[i]) % K;
            int move = Math.min(need, students[i + 1]);
            students[i + 1] -= move;
            students[i] += move;
        }
        int result = 0;
        for (int i = 0; i < n; i++)
            result += (students[i] + fromDown[i] + K - 1) / K;
        return result;
    }
}
