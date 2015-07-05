public class CandyShop {

    public int countProbablePlaces(int[] X, int[] Y, int[] R) {

        for (int i = 0; i < X.length; i++) {
            X[i] += 200;
            Y[i] += 200;
        }

        boolean[][] inter = getRange(X[0], Y[0], R[0]);

        for (int i = 1; i < X.length; i++) {
            boolean[][] range = getRange(X[i], Y[i], R[i]);
            for (int m = 0; m < 401; m++)
                for (int n = 0; n < 401; n++)
                    if (inter[m][n] && (!range[m][n]))
                        inter[m][n] = false;
        }

        int count = 0;
        for (int i = 0; i < 401; i++)
            for (int j = 0; j < 401; j++)
                if (inter[i][j])
                    count++;

        return count;
    }

    public boolean[][] getRange(int x, int y, int r) {
        int disx;
        int disy;
        boolean[][] range = new boolean[401][401];
        for (int i = 0; i < 401; i++)
            for (int j = 0; j < 401; j++) {
                disx = i > x ? i - x : x - i;
                disy = j > y ? j - y : y - j;
                if (disx + disy <= r)
                    range[i][j] = true;
            }
        return range;
    }

}
