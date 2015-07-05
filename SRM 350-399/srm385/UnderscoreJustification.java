public class UnderscoreJustification {
    public String justifyLine(String[] words, int width) {
        int charCount = 0;
        for (int i = 0; i < words.length; i++)
            charCount += words[i].length();
        int gapNumber = words.length - 1;
        int gapChar = width - charCount;
        int bigGap = gapChar % gapNumber;
        int gapLength = gapChar / gapNumber;
        String gap = "";
        for (int i = 0; i < gapLength; i++)
            gap += '_';
        String result = words[0];
        for (int i = 1; i < words.length; i++) {
            if (bigGap > 0) {
                if (words[i].charAt(0) > '_' || words.length - i == bigGap) {
                    result += gap + '_';
                    bigGap--;
                }
                else
                    result += gap;
            }
            else
                result += gap;
            result += words[i];
        }
        return result;
    }
}
