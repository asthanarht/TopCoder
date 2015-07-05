public class MagicalGirlLevelTwoDivTwo {
    public String isReachable(int[] jumpTypes, int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);
        boolean found = false;
        for (int i = 0; i < jumpTypes.length; i++) {
            if (jumpTypes[i] % 2 == 0)
                return "YES";
            else
                found = true;
        }
        if (found && (x + y) % 2 == 0) {
            return "YES";
        }
        return "NO";
    }
}
