public class CircleMarket {
    public int makePurchases(int[] openTime, int[] closeTime, int travelTime) {
        int n = openTime.length;
        boolean[] visited = new boolean[n];
        int result = 0;
        int position = 0;
        int time = 0;
        do {
            if (!visited[position] && time >= openTime[position]
                    && time <= closeTime[position]) {
                result++;
                visited[position] = true;
            }
            else {
                position++;
                if (position == n)
                    position = 0;
                time += travelTime;
            }
        } while (result < n && time <= 1000000);
        return result;
    }
}
