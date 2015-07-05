public class FoxAndMp3 {
    private static int[] name = new int[10];
    private static int digit = 0;
    private static int N;

    public static String[] playList(int n) {
        N = n;
        int num = Math.min(n, 50);
        String[] result = new String[num];
        name[0] = 1;
        int index = 0;
        while (true) {
            result[index] = decimal() + ".mp3";
            index++;
            if (index == num)
                break;
            if (couldAddDigit())
                digit++;
            else if (couldAdd())
                name[digit]++;
            else {
                while (!couldAdd()) {
                    name[digit] = 0;
                    digit--;
                }
                name[digit]++;
            }
        }
        return result;
    }

    private static long decimal() {
        long decimal = 0;
        for (int i = 0; i <= digit; i++) {
            decimal *= 10;
            decimal += name[i];
        }
        return decimal;
    }

    private static boolean couldAdd() {
        if (name[digit] < 9 && decimal() < N)
            return true;
        return false;
    }

    private static boolean couldAddDigit() {
        if (decimal() * 10 <= N)
            return true;
        return false;
    }
}
