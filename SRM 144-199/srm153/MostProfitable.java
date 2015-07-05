public class MostProfitable {
    public String bestItem(int[] costs, int[] prices, int[] sales,
            String[] items) {
        int n = costs.length;
        int max = 0;
        int maxindex = 0;
        for (int i = 0; i < n; i++) {
            int profit = (prices[i] - costs[i]) * sales[i];
            if (profit > max) {
                max = profit;
                maxindex = i;
            }
        }
        if (max == 0)
            return "";
        else
            return items[maxindex];
    }
}
