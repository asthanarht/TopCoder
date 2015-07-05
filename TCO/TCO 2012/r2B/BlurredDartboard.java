public class BlurredDartboard {
    public int minThrows(int[] points, int P) {
        int sum = points.length, max = 0, count = 0;
        boolean[] num = new boolean[points.length + 1];
        for (int i = 0; i < points.length; i++) {
            max = Math.max(points[i], max);
            sum += i;
            if (points[i] == 0)
                count++;
            else {
                sum -= points[i];
                num[points[i]] = true;
            }
        }
        if (count * max >= sum)
            return (P + max - 1) / max;
        else {
            int res = count * (P / sum);
            P = P % sum;
            int n = Integer.MAX_VALUE;
            if (max != 0)
                n = (P + max - 1) / max;
            int plus = 0, need = 0;
            for (int i = 1; i <= points.length; i++)
                if (!num[i]) {
                    if (plus >= P)
                        break;
                    plus += i;
                    need++;
                }
            return Math.min(need, n) + res;
        }
    }
}
