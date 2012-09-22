package srm363;

public class MirrorNumber {
    private int count = 0;
    private long a, b;
    private int[] num = new int[19]; // String is not able to finish in 2
                                     // seconds
    private int[] n1 = { 0, 1, 8, 2, 5 }, n2 = { 0, 1, 8, 5, 2 };
    private int len;

    public int count(String A, String B) {
        a = Long.parseLong(A);
        b = Long.parseLong(B);
        for (len = 0; len < B.length(); len++)
            recur(0, len);
        return count;
    }

    private void recur(int left, int right) {
        if (left > right) {
            long m = 0;
            for (int i = 0; i <= len; i++) {
                m *= 10;
                m += num[i];
            }
            if (m >= a && m <= b)
                count++;
        }
        else if (left == right)
            for (int i = 0; i < 3; i++) {
                num[left] = n1[i];
                recur(left + 1, right - 1);
            }
        else
            for (int i = (left > 0 ? 0 : 1); i < 5; i++) {
                num[left] = n1[i];
                num[right] = n2[i];
                recur(left + 1, right - 1);
            }
    }
}
