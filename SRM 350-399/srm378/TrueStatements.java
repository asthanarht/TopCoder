public class TrueStatements {
    public int numberTrue(int[] statements) {
        int[] count = new int[51];
        for (int i = 0; i < statements.length; i++)
            count[statements[i]]++;
        for (int i = 50; i > 0; i--)
            if (count[i] == i)
                return i;
        if (count[0] > 0)
            return -1;
        return 0;
    }
}
