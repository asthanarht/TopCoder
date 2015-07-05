import java.util.HashSet;

public class AvoidingProduct {
    public int[] getTriple(int[] a, int n) {
        HashSet<Integer> A = new HashSet<Integer>();
        for (int i = 0; i < a.length; i++)
            A.add(a[i]);
        int min = Integer.MAX_VALUE;
        int[] xyz = new int[3];
        for (int i = 1; i < A.size() + 10; i++)
            if (!A.contains(i))
                for (int j = 1; j < A.size() + 100; j++)
                    if (!A.contains(j))
                        for (int k = 1; k < 2000; k++)
                            if (!A.contains(k)) {
                                int num = Math.abs(i * j * k - n);
                                if (num < min && num > 0) {
                                    min = num;
                                    xyz[0] = i;
                                    xyz[1] = j;
                                    xyz[2] = k;
                                }
                            }
        return xyz;

    }
}
