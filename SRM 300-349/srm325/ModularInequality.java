package srm325;

import java.util.Arrays;

public class ModularInequality {
    public int countSolutions(int[] A, int P) {
        int n = A.length;
        Arrays.sort(A);
        long good = Long.MAX_VALUE, sum;
        // check each point
        for (int i = 0; i < n; i++) {
            sum = 0;
            for (int j = 0; j < n; j++)
                sum += Math.abs(A[j] - A[i]);
            if (sum <= P) {
                good = A[i];
                break;
            }
        }
        // check segment (linear within each segment)
        if (good == Long.MAX_VALUE) {
            for (int i = 1; i < n; i++)
                if (A[i] - A[i - 1] > 1) {
                    // check segment lower bound
                    int min = A[i - 1] + 1;
                    if (check(A, P, min)) {
                        good = min;
                        break;
                    }
                    long diff1 = Math.abs(sum(A, min) - P);
                    min++;
                    if (check(A, P, min)) {
                        good = min;
                        break;
                    }
                    long diff2 = Math.abs(sum(A, min) - P);
                    if (diff2 < diff1) {
                        min += (diff2 + (diff1 - diff2) - 1) / (diff1 - diff2);
                        if (check(A, P, min)) {
                            good = min;
                            break;
                        }
                    }
                    // check segment upper bound
                    int max = A[i] - 1;
                    if (check(A, P, max)) {
                        good = max;
                        break;
                    }
                    diff1 = Math.abs(sum(A, max) - P);
                    max--;
                    if (check(A, P, max)) {
                        good = max;
                        break;
                    }
                    diff2 = Math.abs(sum(A, max) - P);
                    if (diff2 < diff1) {
                        max -= (diff2 + (diff1 - diff2) - 1) / (diff1 - diff2);
                        if (check(A, P, max)) {
                            good = max;
                            break;
                        }
                    }
                }
        }
        if (good == Long.MAX_VALUE)
            return 0;
        // lower bound
        long left = -10000000000L, lb = good;
        while (left < lb - 1) {
            long m = (left + lb) / 2;
            if (check(A, P, m))
                lb = m;
            else
                left = m;
        }
        // upper bound
        long right = 10000000000L, ub = good;
        while (ub < right - 1) {
            long m = (ub + right) / 2;
            if (check(A, P, m))
                ub = m;
            else
                right = m;
        }
        return (int) (ub - lb + 1);
    }

    private boolean check(int[] A, int P, long m) {
        if (sum(A, m) <= P)
            return true;
        return false;
    }

    private long sum(int[] A, long m) {
        long sum = 0;
        for (int i = 0; i < A.length; i++)
            sum += Math.abs(A[i] - m);
        return sum;
    }
}
