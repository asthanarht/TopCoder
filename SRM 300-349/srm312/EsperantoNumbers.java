public class EsperantoNumbers {
    public String nameThisNumber(int x) {
        String[] num = { "unu", "du", "tri", "kvar", "kvin", "ses", "sep",
                "ok", "nau", "dek" };
        if (x <= 10)
            return num[x - 1];
        else {
            String result = num[9] + " ";
            if (x % 10 == 0)
                result = result.trim();
            else
                result += num[x % 10 - 1];
            if (x / 10 > 1)
                result = num[x / 10 - 1] + result;
            return result;
        }
    }
}
