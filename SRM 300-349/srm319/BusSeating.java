public class BusSeating {
    public double getArrangement(String leftRow, String rightRow) {
        boolean[][] seat = new boolean[2][10];
        for (int i = 0; i < 10; i++) {
            if (leftRow.charAt(i) == '-')
                seat[0][i] = true;
            if (rightRow.charAt(i) == '-')
                seat[1][i] = true;
        }
        double min = Double.MAX_VALUE;
        for (int i = 0; i < 20; i++)
            if (seat[i / 10][i % 10])
                for (int j = i + 1; j < 20; j++)
                    if (seat[j / 10][j % 10])
                        for (int k = j + 1; k < 20; k++)
                            if (seat[k / 10][k % 10]) {
                                double dis = 0;
                                int w = 0, h = 0;
                                // i j
                                w = Math.abs(i / 10 - j / 10) * 2;
                                h = Math.abs(i % 10 - j % 10);
                                dis += Math.sqrt(w * w + h * h);
                                // i k
                                w = Math.abs(i / 10 - k / 10) * 2;
                                h = Math.abs(i % 10 - k % 10);
                                dis += Math.sqrt(w * w + h * h);
                                // j k
                                w = Math.abs(j / 10 - k / 10) * 2;
                                h = Math.abs(j % 10 - k % 10);
                                dis += Math.sqrt(w * w + h * h);
                                min = Math.min(min, dis);
                            }
        return min;
    }
}
