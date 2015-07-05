public class DoorsGame {
    public int determineOutcome(String doors, int trophy) {
        boolean[] doorJohn = new boolean[26];
        boolean[] doorGogo = new boolean[26];
        for (int i = 0; i < doors.length(); i++)
            if (i < trophy)
                doorJohn[doors.charAt(i) - 'A'] = true;
            else
                doorGogo[doors.charAt(i) - 'A'] = true;
        int countJohn = 0, countGogo = 0, common = 0;
        for (int i = 0; i < 26; i++)
            if (doorJohn[i] && doorGogo[i])
                common++;
            else if (doorJohn[i] && !doorGogo[i])
                countJohn++;
            else if (!doorJohn[i] && doorGogo[i])
                countGogo++;
        int move = 0;
        while (countJohn + common > 0 && countGogo + common > 0) {
            if (move % 2 == 0) {
                if (countJohn > 0)
                    countJohn--;
                else
                    common--;
            }
            else {
                if (countGogo > 0)
                    countGogo--;
                else
                    common--;
            }
            move++;
        }
        if (countJohn + common == 0) {
            if (countGogo > 0)
                return move;
            return 0;
        }
        return move * -1;
    }
}
