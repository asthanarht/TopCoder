public class GameOfLifeDivTwo {
    public String theSimulation(String init, int T) {
        char[] s = init.toCharArray();
        int n = init.length();
        char[] copy = new char[n];
        int t = 0;

        while (t < T) {
            boolean change = false;
            for (int i = 1; i < n - 1; i++) {
                int count = 0;
                for (int j = i - 1; j < i + 2; j++)
                    if (s[j] == '1')
                        count++;
                if (count >= 2) {
                    if (s[i] == '0')
                        change = true;
                    copy[i] = '1';
                }
                else {
                    if (s[i] == '1')
                        change = true;
                    copy[i] = '0';
                }
            }

            int count = 0;
            if (s[0] == '1')
                count++;
            if (s[1] == '1')
                count++;
            if (s[n - 1] == '1')
                count++;
            if (count >= 2) {
                if (s[0] == '0')
                    change = true;
                copy[0] = '1';
            }
            else {
                if (s[0] == '1')
                    change = true;
                copy[0] = '0';
            }

            count = 0;
            if (s[0] == '1')
                count++;
            if (s[n - 2] == '1')
                count++;
            if (s[n - 1] == '1')
                count++;
            if (count >= 2) {
                if (s[n - 1] == '0')
                    change = true;
                copy[n - 1] = '1';
            }
            else {
                if (s[n - 1] == '1')
                    change = true;
                copy[n - 1] = '0';
            }

            s = copy.clone();

            if (!change)
                break;
            t++;
        }

        String result = "";
        for (int i = 0; i < n; i++)
            result += s[i];
        return result;
    }
}
