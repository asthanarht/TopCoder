public class MathContest {

    public int countBlack(String ballSequence, int repetitions) {

        int count = 0;
        boolean shift = false;
        boolean reverse = false;
        int left = 0;
        int right = ballSequence.length() * repetitions - 1;
        String stack = "";

        for (int i = 0; i < repetitions; i++)
            stack = stack + ballSequence;

        while (left <= right) {

            char c;

            if (reverse) {
                c = stack.charAt(right);
                right--;
                if ((shift && c == 'W') || (!shift && c == 'B')) {// b
                    count++;
                    shift = !shift;
                }
                else
                    // w
                    reverse = !reverse;
            }
            else {
                c = stack.charAt(left);
                left++;
                if ((shift && c == 'W') || (!shift && c == 'B')) {// b
                    count++;
                    shift = !shift;
                }
                else
                    // w
                    reverse = !reverse;
            }
        }

        return count;

    }

}