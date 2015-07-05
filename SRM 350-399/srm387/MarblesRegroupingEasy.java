public class MarblesRegroupingEasy {
    public int minMoves(String[] boxes) {
        int m = boxes.length;
        int n = boxes[0].length();
        int[][] box = new int[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                box[i][j] = boxes[i].charAt(j) - '0';

        int mix = 0;
        boolean[] is = new boolean[m]; // true if the ith box only has one color
        for (int i = 0; i < m; i++) {
            int countj = 0;
            for (int j = 0; j < n; j++)
                if (box[i][j] > 0)
                    countj++;
            if (countj <= 1)
                is[i] = true;
            else
                mix++;
        }

        // move all the mixed box together
        int[] joker = new int[n];
        for (int i = 0; i < m; i++)
            if (!is[i])
                for (int j = 0; j < n; j++)
                    joker[j] += box[i][j];

        // check the single boxes with single color
        int[] count = new int[n];
        for (int i = 0; i < m; i++)
            if (is[i])
                for (int j = 0; j < n; j++)
                    if (box[i][j] > 0)
                        count[j]++;

        // get the result
        int res = 0;
        for (int i = 0; i < n; i++)
            if (count[i] > 1)
                res += count[i] - 1;
        if (mix == 0)
            res = Math.max(0, res - 1);
        else if (mix > 1)
            res += mix - 1;

        return res;
    }
}
