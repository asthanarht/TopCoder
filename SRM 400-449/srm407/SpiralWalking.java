public class SpiralWalking {

    public int totalPoints(String[] levelMap) {

        int r = levelMap.length;
        int c = levelMap[0].length();
        int[][] map = new int[r][c];
        int sum = 0;
        int total = r * c;
        int location = 1;
        int m = 0;
        int n = 0;

        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt("" + levelMap[i].charAt(j));
            }

        while (true) {
            sum += map[m][n];
            if (total == 1)
                break;
            if (location == 1) {
                if (n < c - 1 && map[m][n + 1] != -1) {
                    map[m][n] = -1;
                    total--;
                    n++;
                }
                else {
                    sum -= map[m][n];
                    map[m][n] = -1;
                    total--;
                    m++;
                    location = 2;
                }
            }
            else if (location == 2) {
                if (m < r - 1 && map[m + 1][n] != -1) {
                    map[m][n] = -1;
                    total--;
                    m++;
                }
                else {
                    sum -= map[m][n];
                    map[m][n] = -1;
                    total--;
                    n--;
                    location = 3;
                }
            }
            else if (location == 3) {
                if (n > 0 && map[m][n - 1] != -1) {
                    map[m][n] = -1;
                    total--;
                    n--;
                }
                else {
                    sum -= map[m][n];
                    map[m][n] = -1;
                    total--;
                    m--;
                    location = 4;
                }
            }
            else {
                if (m > 1 && map[m - 1][n] != -1) {
                    map[m][n] = -1;
                    total--;
                    m--;
                }
                else {
                    sum -= map[m][n];
                    map[m][n] = -1;
                    total--;
                    n++;
                    location = 1;
                }
            }
        }

        return sum;
    }

}
