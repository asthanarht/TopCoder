package srm148;

public class DivisorDigits {
    public int howMany(int number) {
        String s = "" + number;
        char[] c = s.toCharArray();
        int n = c.length;
        int count = 0;
        for (int i = 0; i < n; i++)
            if (c[i] != '0' && number % (c[i] - '0') == 0)
                count++;
        return count;
    }
}
