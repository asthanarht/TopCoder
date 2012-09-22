package srm516;

public class NetworkXZeroOne {
    public String reconstruct(String message) {
        int n = message.length();
        char[] mes = message.toCharArray();
        for (int i = 0; i < n; i++)
            if (mes[i] != '?') {
                for (int j = i + 1; j < n; j++) {
                    if (mes[j - 1] == 'x')
                        mes[j] = 'o';
                    else
                        mes[j] = 'x';
                }
                for (int j = i - 1; j >= 0; j--) {
                    if (mes[j + 1] == 'x')
                        mes[j] = 'o';
                    else
                        mes[j] = 'x';
                }
                break;
            }
        return new String(mes);
    }
}
