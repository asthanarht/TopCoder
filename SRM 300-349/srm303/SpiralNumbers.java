public class SpiralNumbers {
    public String getPosition(int N) {
        if (N == 1)
            return "(0,0)";
        for (int i = 1, j = 3; i < N; i = j, j += 2) {
            long low = i * i;
            long high = j;
            high *= high;
            if (low < N && high >= N) {
                if (N - low < j)
                    return "(" + (N - low - j / 2) + "," + (j / 2) + ")";
                else if (N - low < j + j - 1)
                    return "(" + (j / 2) + "," + (low + j + j / 2 - 1 - N)
                            + ")";
                else if (N - low < j + j + j - 2)
                    return "(" + (low + j + j + j / 2 - 2 - N) + ",-" + (j / 2)
                            + ")";
                else
                    return "(-" + (j / 2) + "," + (N - high + j / 2) + ")";
            }
        }
        return null;
    }
}
