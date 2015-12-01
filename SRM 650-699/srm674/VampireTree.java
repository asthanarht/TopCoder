import java.util.Arrays;

public class VampireTree {

    public static void main(String[] args) {
        System.out.println(maxDistance(new int[] { 1, 2, 1 }));
        System.out.println(maxDistance(new int[] { 2, 2, 2 }));
        System.out.println(maxDistance(new int[] { 1, 1, 1, 1, 4 }));
        System.out.println(maxDistance(new int[] { 1, 1, 2, 3, 4, 5, 6, 7, 8,
                9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19 }));

    }

    public static int maxDistance(int[] num) {
        int n = num.length;
        Arrays.sort(num);
        for (int i = 0; i < n - 1; i++)
            num[i]--;
        int sum = 0;
        for (int i = 0; i < n; i++)
            sum += num[i];
        if (sum != n - 1)
            return -1;
        int cnt0 = 0;
        for (int i = 0; i < n - 1; i++)
            if (num[i] == 0)
                cnt0++;
        if (num[n - 1] == 1)
            return n - cnt0;
        else
            return n + 1 - cnt0;
    }
}
