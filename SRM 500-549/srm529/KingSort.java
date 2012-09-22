package srm529;

public class KingSort {
    public String[] getSortedList(String[] kings) {
        for (int i = 0; i < kings.length - 1; i++)
            for (int j = 0; j < kings.length - 1 - i; j++)
                if (compare(kings[j + 1], kings[j]) < 0) {
                    String hold = kings[j];
                    kings[j] = kings[j + 1];
                    kings[j + 1] = hold;
                }
        return kings;
    }

    private int compare(String a, String b) {
        String[] as = a.split(" ");
        String[] bs = b.split(" ");
        int result = as[0].compareTo(bs[0]);
        if (result == 0)
            return crn(as[1], bs[1]);
        else
            return result;
    }

    private int crn(String a, String b) {
        int av = translate(a);
        int bv = translate(b);
        if (av > bv)
            return 1;
        else if (av < bv)
            return -1;
        else
            return 0;
    }

    private int translate(String s) {
        String[] digits = { "I", "II", "III", "IV", "V", "VI", "VII", "VIII",
                "IX" };
        String[] tens = { "X", "XX", "XXX", "XL", "L" };
        int value = 0;
        if (s.startsWith("X") || s.contains("L")) {
            int indexI = s.indexOf("I");
            int indexV = s.indexOf("V");
            int index = indexI;
            if (index == -1 || (indexV > 0 && indexV < indexI))
                index = indexV;
            String ten = "";
            if (index > 0) {
                ten = s.substring(0, index);
                String digit = s.substring(index);
                for (int i = 0; i < digits.length; i++)
                    if (digit.equals(digits[i])) {
                        value += i + 1;
                        break;
                    }
            }
            else {
                ten = s.substring(0, s.length());
            }
            for (int i = 0; i < tens.length; i++)
                if (ten.equals(tens[i])) {
                    value += (i + 1) * 10;
                    break;
                }
        }
        else {
            for (int i = 0; i < digits.length; i++)
                if (s.equals(digits[i])) {
                    value = i + 1;
                    break;
                }
        }
        return value;
    }
}