package srm561;

import java.util.Arrays;

public class ICPCBalloons {
    public int minRepaintings(int[] balloonCount, String balloonSize,
            int[] maxAccepted) {
        int n = balloonCount.length, m = maxAccepted.length;
        // count and sort available balloons of both size
        int[] balloon_mid = new int[50], balloon_lar = new int[50];
        int total_mid = 0, total_lar = 0;
        for (int i = 0, j = 0, k = 0; k < n; k++) {
            if (balloonSize.charAt(k) == 'M') {
                balloon_mid[i++] = balloonCount[k];
                total_mid += balloonCount[k];
            }
            else {
                balloon_lar[j++] = balloonCount[k];
                total_lar += balloonCount[k];
            }
        }
        Arrays.sort(balloon_mid);
        Arrays.sort(balloon_lar);

        int min = Integer.MAX_VALUE;
        int[] need_mid = new int[m], need_lar = new int[m];
        // mask is used to determine the balloon size of the kth problem
        for (int mask = 0; mask < (1 << m); mask++) {
            Arrays.fill(need_mid, 0);
            Arrays.fill(need_lar, 0);
            int i = 0, j = 0;
            int counnt_mid = 0, count_lar = 0;
            for (int k = 0; k < m; k++)
                if ((mask & (1 << k)) == 0) {
                    need_mid[i++] = maxAccepted[k];
                    counnt_mid += maxAccepted[k];
                }
                else {
                    need_lar[j++] = maxAccepted[k];
                    count_lar += maxAccepted[k];
                }
            if (counnt_mid > total_mid || count_lar > total_lar)
                continue;
            Arrays.sort(need_mid);
            Arrays.sort(need_lar);

            // match available balloons of the correct size in decreasing order
            // of the number of the balloon
            int[] copy_mid = balloon_mid.clone();
            int[] copy_lar = balloon_lar.clone();
            int repaint = 0;
            for (i = m - 1, j = 49; i >= 0 && need_mid[i] > 0; i--, j--)
                if (need_mid[i] > copy_mid[j])
                    repaint += need_mid[i] - copy_mid[j];
            for (i = m - 1, j = 49; i >= 0 && need_lar[i] > 0; i--, j--)
                if (need_lar[i] > copy_lar[j])
                    repaint += need_lar[i] - copy_lar[j];
            min = Math.min(min, repaint);
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
