package srm473;

public class RightTriangle {
    public static void main(String[] args) {
        System.out.println(triangleCount(1, 1, 21421, 435, 354));
    }

    public static long triangleCount(int places, int points, int a, int b, int c) {
        long A = a, B = b, C = c;
        int[] pos = new int[points];
        for (int i = 0; i < points; i++)
            pos[i] = (int) ((A * i * i + B * i + C) % places);
        int[] red = new int[places];
        for (int i = 0; i < points; i++)
            red[pos[i]] += 1;
        boolean[] reds = new boolean[places];
        for (int i = 0; i < places - 1; i++)
            if (red[i] > 0) {
                reds[i] = true;
                red[i + 1] += red[i] - 1;
                red[i] = 0;
            }
        if (red[places - 1] > 0) {
            reds[places - 1] = true;
            red[0] += red[places - 1] - 1;
            red[places - 1] = 0;
            for (int i = 0; i < places && red[i] > 0; i++)
                if (reds[i])
                    red[i + 1] = red[i];
                else {
                    reds[i] = true;
                    red[i + 1] = red[i] - 1;
                }
        }
        long result = 0;
        int half = places / 2;
        if (places % 2 == 0) {
            for (int i = 0; i < half; i++)
                if (reds[i] && reds[i + half])
                    result++;
        }
        return result * (points - 2);
    }
}
