import java.util.Arrays;

public class SurveillanceSystem {
    public String getContainerInfo(String containers, int[] reports, int L) {
        int n = containers.length();
        Arrays.sort(reports);
        int[] countR = new int[n + 1];
        for (int i = 0; i < reports.length; i++)
            countR[reports[i]]++;
        int[] conts = new int[n];
        Arrays.fill(conts, -1);
        for (int i = 0; i + L <= n; i++) {
            conts[i] = 0;
            for (int j = 0; j < L; j++)
                if (containers.charAt(i + j) == 'X')
                    conts[i]++;
        }
        int countC[] = new int[n + 1];
        for (int i = 0; i < n; i++)
            if (conts[i] != -1)
                countC[conts[i]]++;

        char[] result = new char[n];
        Arrays.fill(result, '0');
        for (int i = 0; i <= n; i++)
            if (countR[i] > 0) {
                int[] count = new int[n];
                for (int j = 0; j < n; j++)
                    if (conts[j] == i)
                        for (int k = 0; k < L; k++)
                            count[j + k]++;
                char[] res = new char[n];
                for (int j = 0; j < n; j++)
                    if (count[j] == 0)
                        res[j] = '-';
                    else if (count[j] - (countC[i] - countR[i]) >= 1)
                        res[j] = '+';
                    else
                        res[j] = '?';
                for (int j = 0; j < n; j++)
                    if (result[j] == '+')
                        continue;
                    else if (result[j] == '-') {
                        if (res[j] == '+' || res[j] == '?')
                            result[j] = res[j];
                    }
                    else if (result[j] == '?') {
                        if (res[j] == '+')
                            result[j] = res[j];
                    }
                    else
                        result[j] = res[j];
            }
        return new String(result);
    }
}
