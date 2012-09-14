package srm144;

public class BinaryCode {

    public String[] decode(String message) {

        int n = message.length();
        int pre = 0;
        int pos = 0;
        String[] ret = new String[2];

        ret[0] = "0";
        ret[1] = "1";

        for (int i = 1; i < n; i++) {
            pos = message.charAt(i - 1) - ret[0].charAt(i - 1) - pre;
            if (pos != 0 && pos != 1) {
                ret[0] = "NONE";
                break;
            }
            ret[0] += pos;
            pre = ret[0].charAt(i - 1) - '0';
        }

        pre = 0;
        for (int i = 1; i < n; i++) {
            pos = message.charAt(i - 1) - ret[1].charAt(i - 1) - pre;
            if (pos != 0 && pos != 1) {
                ret[1] = "NONE";
                break;
            }
            ret[1] += pos;
            pre = ret[1].charAt(i - 1) - '0';
        }

        if (n == 1) {
            if (ret[0].compareTo(message) != 0)
                ret[0] = "NONE";
            if (ret[1].compareTo(message) != 0)
                ret[1] = "NONE";
        }
        else if (n >= 2) {
            if (ret[0].compareTo("NONE") != 0)
                if (message.charAt(n - 1) - ret[0].charAt(n - 1) != ret[0]
                        .charAt(n - 2) - '0')
                    ret[0] = "NONE";
            if (ret[1].compareTo("NONE") != 0)
                if (message.charAt(n - 1) - ret[1].charAt(n - 1) != ret[1]
                        .charAt(n - 2) - '0')
                    ret[1] = "NONE";
        }

        return ret;
    }
}
