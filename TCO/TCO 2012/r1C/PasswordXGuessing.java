package r1C;

import java.util.Arrays;

public class PasswordXGuessing {
    public long howMany(String[] guesses) {
        int n = guesses.length, m = guesses[0].length();
        long res = 0;
        for (int i = 0; i < m; i++) {
            boolean ok = true;
            boolean[] digit = new boolean[10];
            Arrays.fill(digit, true);
            digit[guesses[0].charAt(i) - '0'] = false;
            for (int j = 1; ok && j < n; j++) {
                int count = 0;
                for (int k = 0; k < m; k++)
                    if (k != i && guesses[j].charAt(k) != guesses[0].charAt(k))
                        count++;
                if (count > 1)
                    ok = false;
                else if (count == 1) {
                    if (guesses[j].charAt(i) != guesses[0].charAt(i)) {
                        for (int p = 0; p < 10; p++)
                            if (digit[guesses[j].charAt(i) - '0'] == true) {
                                Arrays.fill(digit, false);
                                digit[guesses[j].charAt(i) - '0'] = true;
                            }
                            else
                                ok = false;
                    }
                    else
                        ok = false;
                }
                else
                    digit[guesses[j].charAt(i) - '0'] = false;
            }
            if (ok)
                for (int p = 0; p < 10; p++)
                    if (digit[p])
                        res++;
        }
        return res;
    }
}
