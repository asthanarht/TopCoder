public class Dating {
    public String dates(String circle, int k) {
        String result = "";
        boolean used[] = new boolean[circle.length()];
        int count = circle.length();
        int cur = 0;
        while (count > 1) {
            int jump = k;
            for (;; cur = (cur + 1) % circle.length())
                if (!used[cur] && --jump == 0)
                    break;
            char chooser = circle.charAt(cur);
            char chosen;
            int chosenIndex = -1;
            char oppositeA;
            if (chooser >= 'a' && chooser <= 'z') {
                chosen = 'Z';
                oppositeA = 'A';
            }
            else {
                chosen = 'z';
                oppositeA = 'a';
            }
            for (int i = 0; i < circle.length(); i++)
                if (!used[i] && circle.charAt(i) >= oppositeA
                        && circle.charAt(i) <= chosen) {
                    chosenIndex = i;
                    chosen = circle.charAt(i);
                }
            if (chosenIndex == -1)
                break;
            else {
                result += " " + chooser + chosen;
                used[cur] = true;
                used[chosenIndex] = true;
                count -= 2;
            }
        }
        return result.trim();
    }
}
