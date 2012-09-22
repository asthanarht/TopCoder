package srm343;

public class CDPlayer {
    public int isRandom(String[] songlist, int n) {
        String all = "";
        for (int i = 0; i < songlist.length; i++)
            all += songlist[i];
        loop: for (int i = 0; i < n; i++) {
            for (int j = i; j < all.length(); j += n) {
                boolean[] check = new boolean[26];
                for (int k = j; k < Math.min(j + n, all.length()); k++) {
                    int c = all.charAt(k) - 'A';
                    if (check[c])
                        continue loop;
                    check[c] = true;
                }
            }
            boolean[] check = new boolean[26];
            for (int k = 0; k < i; k++) {
                int c = all.charAt(k) - 'A';
                if (check[c])
                    continue loop;
                check[c] = true;
            }
            return i;
        }
        return -1;
    }
}
