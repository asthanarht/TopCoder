import java.util.HashMap;

public class RandomSort {
    private int[] permut;
    private HashMap<Integer, Double> dp = new HashMap<Integer, Double>();

    public double getExpected(int[] permutation) {
        permut = permutation.clone();
        return dfs();
    }

    private double dfs() {
        int sign = signature();
        if (dp.containsKey(sign))
            return dp.get(sign);
        double total = 0;
        int times = 0;
        for (int i = 0; i < permut.length; i++)
            for (int j = i + 1; j < permut.length; j++)
                if (permut[i] > permut[j]) {
                    int hold = permut[i];
                    permut[i] = permut[j];
                    permut[j] = hold;
                    total += dfs() + 1;
                    times++;
                    permut[j] = permut[i];
                    permut[i] = hold;
                }
        double expect = times == 0 ? 0 : total / times;
        dp.put(sign, expect);
        return expect;
    }

    private int signature() {
        int sign = 0;
        for (int i = 0; i < permut.length; i++)
            sign = sign * 10 + permut[i];
        return sign;
    }
}
