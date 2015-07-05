import java.util.Arrays;

public class CollectingUsualPostmarks {
    public int numberOfPostmarks(int[] prices, int[] have) {
        int money = 0;
        for (int i = 0; i < have.length; i++)
            money += prices[have[i]];
        Arrays.sort(prices);
        int count = 0;
        for (int i = 0; i < prices.length; i++)
            if (money >= prices[i]) {
                money -= prices[i];
                count++;
            }
            else
                break;
        return count;
    }
}
