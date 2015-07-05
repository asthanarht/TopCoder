public class JumpFurther {
    public int furthest(int N, int badStep) {
        int sum = 0;
        for (int i = 1; i <= N; i++) {
            sum += i;
            if (sum == badStep)
                sum -= 1;
        }
        return sum;
    }
}
