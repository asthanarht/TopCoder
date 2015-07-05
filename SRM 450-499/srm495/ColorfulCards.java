import java.util.Arrays;

public class ColorfulCards {
    boolean[] prime;

    public int[] theCards(int N, String colors) {
        getPrime(N);
        int len = colors.length();
        int[] result = new int[len];
        int start = 1;
        for (int i = 0; i < len; i++) {
            boolean found = false;
            char c = colors.charAt(i);
            for (int j = start; j <= N; j++)
                if ((c == 'R' && prime[j]) || (c == 'B' && !prime[j])) {
                    if (!found) {
                        found = true;
                        result[i] = j;
                        start = j + 1;
                    }
                    else {
                        int n = j + 1, ii = i + 1;
                        while (ii < len) {
                            char cc = colors.charAt(ii);
                            while (n <= N
                                    && ((cc == 'B' && prime[n]) || (cc == 'R' && !prime[n])))
                                n++;
                            if (n <= N
                                    && ((cc == 'R' && prime[n]) || (cc == 'B' && !prime[n]))) {
                                n++;
                                ii++;
                            }
                            else
                                break;
                        }
                        if (ii == len) {
                            result[i] = -1;
                            break;
                        }
                    }
                }
        }
        return result;
    }

    public void getPrime(int n) {
        prime = new boolean[n + 1];
        Arrays.fill(prime, true);
        prime[1] = false;
        for (int i = 2; i <= n; i++)
            if (prime[i])
                for (int j = i + i; j <= n; j += i)
                    prime[j] = false;
    }
}
