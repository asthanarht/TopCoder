public class PerfectSequences {

    public String fixIt(int[] seq) {

        int sum = 0;
        double mul = 1;
        int count1 = 0;
        int count2 = 0;

        // if the sequence only has one element
        int length = seq.length;
        if (length == 1)
            return "Yes";

        for (int i = 0; i < length; i++) {
            sum += seq[i];
            if (seq[i] == 0)
                count1++;
            else {
                count2++;
                mul *= seq[i];
            }
        }

        // if there is only one element which is not 0
        if (count2 == 1)
            return "Yes";

        // if there is 2 or more none 0 elements
        // and if there is 2 or more 0s
        if (count1 > 1)
            return "No";

        for (int i = 0; i < length; i++) {
            // s is the sum of all the other elements
            int s = sum - seq[i];
            // m is the product of all the other elements
            double m;
            if (seq[i] == 0) {
                if (count1 == 1)
                    m = mul;
                else
                    m = 0;
            }
            else {
                if (count1 > 0)
                    m = 0;
                else
                    m = mul / seq[i];
            }

            // in these two cases, you will never find a j that s + j == m * j
            // no need to go into the for loop for testing each integer
            if (m == 0 && s > 0)
                continue;
            if (m == 1 && s > 0)
                continue;

            for (int j = 0; j < Integer.MAX_VALUE; j++)
                // you can not change an element to itself
                if (j != seq[i]) {
                    if (s + j == m * j)
                        return "Yes";
                    // when you meet a j that m * j > s + j
                    // you will never find a j bigger than this j such that
                    // s + j == m * j
                    if (m * j > s + j)
                        break;
                }
        }

        return "No";
    }
}
