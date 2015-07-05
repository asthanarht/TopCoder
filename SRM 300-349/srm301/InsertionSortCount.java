public class InsertionSortCount {
    public int countMoves(int[] A) {
        int move = 0;
        int[] R = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            int j = 0;
            for (; j < i; j++)
                if (R[j] > A[i])
                    break;
            if (j != i)
                for (int k = i - 1 < A.length - 2 ? i - 1 : A.length - 2; k >= j; k--) {
                    R[k + 1] = R[k];
                    move++;
                }
            R[j] = A[i];
        }
        return move;
    }
}
