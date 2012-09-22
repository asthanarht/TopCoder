package srm379;

public class SellingProducts {
    public int optimalPrice(int[] price, int[] cost) {
        int n = price.length;
        int max = 0;
        for (int i = 0; i < n; i++)
            max = Math.max(max, price[i]);
        int maxProfit = 0;
        int best = 0;
        for (int charge = 1; charge <= max; charge++) {
            int sale = 0;
            for (int i = 0; i < n; i++)
                if (price[i] >= charge && charge > cost[i])
                    sale += charge - cost[i];
            if (sale > maxProfit) {
                maxProfit = sale;
                best = charge;
            }
        }
        return best;
    }
}
