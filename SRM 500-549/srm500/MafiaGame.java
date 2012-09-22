package srm500;

public class MafiaGame {
    public static double probabilityToLose(int N, int[] decisions) {
        int vote[] = new int[N];
        for (int i : decisions)
            vote[i]++;
        int max = 0;
        for (int i : vote)
            if (i > max)
                max = i;
        if (max == 1)
            return 0;
        int countMax = 0;
        for (int i : vote)
            if (i == max)
                countMax++;
        int vul = countMax;
        while (vul != 1) {
            vul = N % vul;
            if (vul == 0)
                return 0;
        }
        return 1D / countMax;
    }
}
