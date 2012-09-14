package srm146;

import java.util.HashSet;

public class Masterbrain {

    public int possibleSecrets(String[] guesses, String[] results) {
        String answer = "1111";
        HashSet<String> one = new HashSet<String>();
        int n = guesses.length;
        int count = 0;
        do {
            count = 0;
            for (int i = 0; i < n; i++)
                if (check(guesses[i], results[i], answer))
                    count++;
            if (count == n - 1)
                one.add(answer);
            answer = add(answer);
        } while (answer.compareTo("1111") != 0);
        return one.size();
    }

    private boolean check(String guess, String clue, String answer) {
        int countb = 0;
        int countw = 0;
        char[] g = guess.toCharArray();
        char[] a = answer.toCharArray();
        for (int i = 0; i < 4; i++)
            if (g[i] == a[i]) {
                g[i] = 'a';
                a[i] = 'a';
                countb++;
            }
        for (int i = 0; i < 4; i++)
            if (g[i] != 'a')
                for (int j = 0; j < 4; j++)
                    if (g[i] == a[j]) {
                        a[j] = 'a';
                        countw++;
                        break;
                    }
        if (clue.charAt(0) - '0' == countb && clue.charAt(3) - '0' == countw)
            return true;
        return false;
    }

    private String add(String answer) {
        char[] a = answer.toCharArray();
        for (int i = 3; i >= 0; i--)
            if (a[i] != '7') {
                a[i]++;
                break;
            }
            else
                a[i] = '1';
        return "" + a[0] + a[1] + a[2] + a[3];
    }
}
