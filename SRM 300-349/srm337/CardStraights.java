package srm337;

import java.util.Arrays;

public class CardStraights {
    public int longestStraight(int[] cards) {
        Arrays.sort(cards);
        int n = cards.length, joker = 0, m = n;
        for (int i = 0; i < n; i++) {
            if (cards[i] == 0)
                joker++;
            else if (i != 0 && cards[i] == cards[i - 1]) {
                cards[i - 1] = Integer.MAX_VALUE;
                m--;
            }
        }
        Arrays.sort(cards);
        int max = joker;
        for (int i = joker; i < m; i++)
            for (int j = i; j < m; j++) {
                int range = cards[j] - cards[i] + 1;
                int card = j - i + 1;
                if (range - card <= joker)
                    max = Math.max(max, joker + card);
            }
        return max;
    }
}
