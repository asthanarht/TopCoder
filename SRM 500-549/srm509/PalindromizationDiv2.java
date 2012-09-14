package srm509;

public class PalindromizationDiv2 {

    public int getMinimumCost(int X) {

        for (int i = 0; i < Integer.MAX_VALUE - X; i++)
            if (isP(X + i))
                return i;
            else if (X >= i && isP(X - i))
                return i;

        return 0;

    }

    public boolean isP(int num) {

        String s = "" + num;
        int n = s.length();

        for (int i = 0; i < n / 2; i++)
            if (s.charAt(i) != s.charAt(n - 1 - i))
                return false;

        return true;
    }

}
