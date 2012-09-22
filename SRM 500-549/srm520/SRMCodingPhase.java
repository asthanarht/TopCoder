package srm520;

import java.util.Arrays;

public class SRMCodingPhase {
    public int countScore(int[] points, int[] skills, int luck) {
        return score(points, skills[0], skills[1], skills[2], luck);
    }

    private int score(int[] points, int s0, int s1, int s2, int luck) {
        int scores[] = new int[10];
        if (s0 <= 75)
            scores[0] = points[0] - 2 * s0;
        if (s1 <= 75)
            scores[1] = points[1] - 4 * s1;
        if (s2 <= 75)
            scores[2] = points[2] - 8 * s2;
        if (s0 + s1 <= 75)
            scores[3] = scores[0] + scores[1];
        if (s0 + s2 <= 75)
            scores[4] = scores[0] + scores[2];
        if (s1 + s2 <= 75)
            scores[5] = scores[1] + scores[2];
        if (s0 + s1 + s2 <= 75)
            scores[6] = scores[0] + scores[1] + scores[2];
        if (luck > 0) {
            if (s0 > 1) {
                int min = Math.min(s0 - 1, luck);
                scores[7] = score(points, s0 - min, s1, s2, luck - min);
            }
            if (s1 > 1) {
                int min = Math.min(s1 - 1, luck);
                scores[8] = score(points, s0, s1 - min, s2, luck - min);
            }
            if (s2 > 1) {
                int min = Math.min(s2 - 1, luck);
                scores[9] = score(points, s0, s1, s2 - min, luck - min);
            }
        }
        Arrays.sort(scores);
        return scores[9];
    }
}
