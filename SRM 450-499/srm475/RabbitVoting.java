public class RabbitVoting {
    public String getWinner(String[] names, String[] votes) {
        int[] count = new int[names.length];
        for (int i = 0; i < votes.length; i++)
            for (int j = 0; j < names.length; j++)
                if (names[j].equals(votes[i]) && i != j) {
                    count[j]++;
                    break;
                }
        int max = 0;
        int maxIndex = 0;
        boolean valid = true;
        for (int i = 0; i < count.length; i++)
            if (count[i] > max) {
                max = count[i];
                maxIndex = i;
                valid = true;
            }
            else if (count[i] == max)
                valid = false;
        if (valid)
            return names[maxIndex];
        else
            return "";
    }
}
