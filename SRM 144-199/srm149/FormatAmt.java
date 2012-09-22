package srm149;

public class FormatAmt {
    public String amount(int dollars, int cents) {
        String s = "$";
        if (dollars == 0)
            s += "0.";
        else {
            String d = "" + dollars;
            char[] c = d.toCharArray();
            int n = c.length;
            int r = n % 3;
            int cur = 0;
            if (r != 0) {
                while (cur < r)
                    s += c[cur++];
            }
            while (cur < n) {
                if (s.charAt(s.length() - 1) != '$')
                    s += ",";
                for (int j = 0; j < 3; j++)
                    s += c[cur++];
            }
            s += ".";
        }
        if (cents > 9)
            s += cents;
        else if (cents == 0)
            s += "00";
        else
            s += "0" + cents;
        return s;
    }
}
