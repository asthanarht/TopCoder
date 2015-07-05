import java.util.HashSet;

public class MNS {
    public int combos(int[] numbers) {
        int sum = 0;
        for (int i = 0; i < 9; i++)
            sum += numbers[i];
        if (sum % 3 != 0)
            return 0;
        int ave = sum / 3;
        HashSet<String> set = new HashSet<String>();

        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (j != i)
                    for (int k = 0; k < 9; k++)
                        if (k != i && k != j
                                && numbers[i] + numbers[j] + numbers[k] == ave)

                            for (int l = 0; l < 9; l++)
                                if (l != i && l != j && l != k)
                                    for (int m = 0; m < 9; m++)
                                        if (m != i && m != j && m != k
                                                && m != l)
                                            for (int n = 0; n < 9; n++)
                                                if (n != i
                                                        && n != j
                                                        && n != k
                                                        && n != l
                                                        && n != m
                                                        && numbers[l]
                                                                + numbers[m]
                                                                + numbers[n] == ave)

                                                    for (int o = 0; o < 9; o++)
                                                        if (o != i && o != j
                                                                && o != k
                                                                && o != l
                                                                && o != m
                                                                && o != n)
                                                            for (int p = 0; p < 9; p++)
                                                                if (p != i
                                                                        && p != j
                                                                        && p != k
                                                                        && p != l
                                                                        && p != m
                                                                        && p != n
                                                                        && p != o)
                                                                    for (int q = 0; q < 9; q++)
                                                                        if (q != i
                                                                                && q != j
                                                                                && q != k
                                                                                && q != l
                                                                                && q != m
                                                                                && q != n
                                                                                && q != o
                                                                                && q != p)
                                                                            if (numbers[o]
                                                                                    + numbers[p]
                                                                                    + numbers[q] == ave)
                                                                                if (numbers[i]
                                                                                        + numbers[l]
                                                                                        + numbers[o] == ave)
                                                                                    if (numbers[j]
                                                                                            + numbers[m]
                                                                                            + numbers[p] == ave)
                                                                                        if (numbers[k]
                                                                                                + numbers[n]
                                                                                                + numbers[q] == ave) {
                                                                                            String s = ""
                                                                                                    + numbers[i]
                                                                                                    + numbers[j]
                                                                                                    + numbers[k]
                                                                                                    + numbers[l]
                                                                                                    + numbers[m]
                                                                                                    + numbers[n]
                                                                                                    + numbers[o]
                                                                                                    + numbers[p]
                                                                                                    + numbers[q];
                                                                                            set.add(s);
                                                                                        }

        return set.size();
    }
}
