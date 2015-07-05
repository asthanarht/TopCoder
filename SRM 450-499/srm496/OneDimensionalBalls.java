import java.util.Arrays;
import java.util.HashSet;

public class OneDimensionalBalls {
    public long countValidGuesses(int[] firstPicture, int[] secondPicture) {
        Arrays.sort(firstPicture);
        Arrays.sort(secondPicture);
        HashSet<Integer> dis = new HashSet<Integer>();
        int F = firstPicture.length, S = secondPicture.length;
        for (int v = 0; v < S; v++)
            if (firstPicture[0] != secondPicture[v])
                dis.add(Math.abs(firstPicture[0] - secondPicture[v]));
        long result = 0;
        for (Integer d : dis) {
            /*
             * for each potential distance, find number of possible
             * alternatives.
             */
            // p is a multiply of f(path[i])
            // or 0 if any point in leftPicture cannot be matched.
            long p = 1;
            boolean[] fp = new boolean[F];
            boolean[] sp = new boolean[S];
            for (int i = 0; i < F; i++)
                if (!fp[i]) {
                    /*
                     * for each unmatched point in firstPicture, find a path
                     * starting at this point or this point - d in secondPicture
                     */
                    // current leftPicture point and its index
                    int cur = firstPicture[i], curi = i;
                    // how many points of the path comes from firstPicture
                    int left = 0;
                    // how many points of the path comes from secondPicture
                    int right = (find(secondPicture, cur - d) == -1 ? 0 : 1);
                    while (curi != -1) {
                        fp[curi] = true;
                        left++;
                        int n = find(secondPicture, cur + d);
                        if (n == -1)
                            break;
                        sp[n] = true;
                        right++;
                        cur += d * 2;
                        curi = find(firstPicture, cur);
                    }
                    // if left + 1 == right, this path have right number of
                    // alternatives
                    if (left < right)
                        p *= right;
                    // left > right, some point of the firstPicture cannot be
                    // matched
                    if (left > right)
                        p = 0;
                    // left == right, only 1 alternative in this path, p *= 1
                }
            result += p;
        }
        return result;
    }

    public int find(int[] array, int value) {
        for (int i = 0; i < array.length; i++)
            if (array[i] == value)
                return i;
        return -1;
    }
}

// Examples
// 4)
// {6,2,4}
// {1,2,3,4,5,7,8}
// Returns: 7

// distance = 1
// p = 1;
// __1
// 2/
// _\3
// 4/
// _\5
// 6/
// _\7
// left == 3, right == 4
// p *= 4, p == 4
// result += 4;

// distance = 2
// p = 1;
// 2\
// __4
// 6/
// _\8
// left == 2, right == 2
// p *= 1;
// __2
// 4/
// left == 1, right == 1
// p *= 1;
// result += 1;

// distance = 3
// p = 1;
// 2\
// __5
// left == 1, right == 1
// p *= 1;
// __1
// 4/
// _\7
// left == 1, right == 2;
// p *= 2; p == 2
// __3
// 6/
// left == 1, right == 1;
// p *= 1, p == 2;
// result += 2;