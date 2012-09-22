package srm512;

import java.util.HashSet;

public class MysteriousRestaurant {

    private int N;
    private int M;
    private int end = 0;
    private int[] select;
    private int[][] price;
    private int[] spend;
    private int count = 0;

    public int maxDays(String[] prices, int budget) {
        N = prices.length;
        M = prices[0].length();
        price = new int[N][M];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++) {
                char c = prices[i].charAt(j);
                if (c >= '0' && c <= '9')
                    price[i][j] = c - '0';
                else if (c >= 'A' && c <= 'Z')
                    price[i][j] = c - 'A' + 10;
                else
                    price[i][j] = c - 'a' + 36;
            }

        select = new int[N];
        spend = new int[N];

        for (end = 0; end < N; end++) {
            getj(end);
            gets();
            if (spend[end] <= budget)
                count = end + 1;
            else
                break;
        }

        return count;
    }

    private void gets() {
        int total = 0;
        for (int i = 0; i <= end; i++)
            total += price[i][select[i]];
        spend[end] = total;
    }

    private void getj(int day) {
        HashSet<Integer> days = new HashSet<Integer>();
        for (int i = 0; i <= end; i++)
            if (i % 7 == day % 7)
                days.add(i);
        int min = Integer.MAX_VALUE;
        int minj = 0;
        for (int j = 0; j < M; j++) {
            int total = 0;
            for (Integer i : days)
                total += price[i][j];
            if (total < min) {
                min = total;
                minj = j;
            }
        }
        for (Integer i : days)
            select[i] = minj;
    }
}