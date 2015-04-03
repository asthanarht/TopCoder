package reusable;

public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        int[] x = { 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15 };
        LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
        int[] rst = lis.nlogn(x);
        for (int i = 0; i < rst.length; i++)
            System.out.print(rst[i] + " ");
    }

    public int[] nlogn(int[] x) {
        int n = x.length;
        int[] pre = new int[n];
        int[] endi = new int[n + 1];
        int len = 0;
        for (int i = 0; i < n; i++) {
            int left = 1, right = len;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                // if (x[endi[mid]] <= x[i]) // non decreasing subsequence
                if (x[endi[mid]] < x[i])
                    left = mid + 1;
                else
                    right = mid - 1;
            }
            int nlen = left;
            pre[i] = endi[nlen - 1];
            endi[nlen] = i;
            if (nlen > len)
                len = nlen;
        }

        int[] lis = new int[len];
        int last = endi[len];
        for (int i = len - 1; i >= 0; i--) {
            lis[i] = x[last];
            last = pre[last];
        }

        return lis;
    }

}
