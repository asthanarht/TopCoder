public class VendingMachine {

    public int motorUse(String[] prices, String[] purchases) {

        int r = prices.length;
        String[] s = prices[0].split(" ");
        int c = s.length;
        int num = 0;
        int[][] price = new int[r][c];
        int[] sub = new int[c];
        int cur = 0;
        int des = 0;
        int len = purchases.length;
        int[] m = new int[len];
        int[] n = new int[len];
        int[] t = new int[len];
        int count = 0;

        for (int i = 0; i < r; i++) {
            s = prices[i].split(" ");
            for (int j = 0; j < c; j++) {
                num = Integer.parseInt(s[j]);
                price[i][j] = num;
                sub[j] += num;
            }
        }

        for (int i = 0; i < len; i++) {
            m[i] = Integer.parseInt(purchases[i].substring(0,
                    purchases[i].indexOf(",")));
            n[i] = Integer.parseInt(purchases[i].substring(
                    purchases[i].indexOf(",") + 1, purchases[i].indexOf(":")));
            t[i] = Integer.parseInt(purchases[i].substring(purchases[i]
                    .indexOf(":") + 1));
        }

        des = getMax(sub);
        count += dis(cur, des, c);
        cur = des;
        for (int i = 0; i < len; i++) {
            des = n[i];
            count += dis(cur, des, c);
            cur = des;
            if (price[m[i]][des] == 0)
                return -1;
            sub[des] -= price[m[i]][des];
            price[m[i]][des] = 0;
            if (i == len - 1 || t[i + 1] - t[i] >= 5) {
                des = getMax(sub);
                count += dis(cur, des, c);
                cur = des;
            }
        }

        return count;
    }

    private int getMax(int[] sub) {
        int max = 0;
        int index = 0;
        for (int i = 0; i < sub.length; i++)
            if (sub[i] > max) {
                max = sub[i];
                index = i;
            }
        return index;
    }

    private int dis(int cur, int des, int c) {
        int dis = Math.abs(cur - des);
        return Math.min(dis, c - dis);
    }

}
