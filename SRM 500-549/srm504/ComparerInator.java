package srm504;

public class ComparerInator {

    public int makeProgram(int[] A, int[] B, int[] wanted) {

        int length = A.length;
        boolean ok = true;

        for (int i = 0; i < length; i++)
            if (A[i] != wanted[i]) {
                ok = false;
                break;
            }

        if (ok)
            return 1;

        ok = true;

        for (int i = 0; i < length; i++)
            if (B[i] != wanted[i]) {
                ok = false;
                break;
            }

        if (ok)
            return 1;

        ok = true;

        for (int i = 0; i < length; i++) {
            int small = A[i] < B[i] ? A[i] : B[i];
            if (small != wanted[i]) {
                ok = false;
                break;
            }
        }

        if (ok)
            return 7;

        ok = true;

        for (int i = 0; i < length; i++) {
            int big = A[i] < B[i] ? B[i] : A[i];
            if (big != wanted[i]) {
                ok = false;
                break;
            }
        }

        if (ok)
            return 7;

        return -1;

    }

}
