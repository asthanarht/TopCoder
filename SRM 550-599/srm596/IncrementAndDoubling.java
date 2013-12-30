package srm596;

public class IncrementAndDoubling {
    public int getMin(int[] desiredArray) {
        int n = desiredArray.length;
        int maxD = 0, res = 0;
        for (int i = 0; i < n; i++) {
            int num = desiredArray[i];
            res += Integer.bitCount(num);
            for (int j = 0; j < 32; j++)
                if ((1 << j) > num) {
                    maxD = Math.max(maxD, j - 1);
                    break;
                }
        }
        return res + maxD;
    }
}
