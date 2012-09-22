package srm520;

import java.util.Arrays;

public class SRMRoomAssignmentPhase {
    public int countCompetitors(int[] ratings, int K) {
        int mine = ratings[0];
        Arrays.sort(ratings);
        int count = 0;
        for (int i = ratings.length - 1; i >= 0; i--)
            if (ratings[i] > mine) {
                count++;
            }
        return count / K;
    }
}
