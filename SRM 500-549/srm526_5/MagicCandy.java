public class MagicCandy {
    public int whichOne(int n) {
        int last;
        for (last = 1; last * last <= n; last++)
            ;
        last--;
        int res = last * last + 1;
        res += res + last <= n ? last : 0;
        res -= res <= n ? 0 : last;
        return res;
    }
}
