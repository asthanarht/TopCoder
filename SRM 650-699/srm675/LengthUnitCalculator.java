public class LengthUnitCalculator {

    public double calc(int amount, String fromUnit, String toUnit) {
        String[] name = new String[] { "mi", "yd", "ft", "in" };
        int[] ratio = new int[] { 1760, 3, 12 };
        int fi = 0, ti = 0;
        while (name[fi].compareTo(fromUnit) != 0)
            fi++;
        while (name[ti].compareTo(toUnit) != 0)
            ti++;
        double res = amount;
        while (fi < ti)
            res *= ratio[fi++];
        while (fi > ti)
            res /= ratio[ti++];
        return res;
    }

}
