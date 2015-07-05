import java.util.Arrays;

public class InstantRunoffVoting {
    public int winner(String[] voters) {
        boolean[] candidates = new boolean[voters[0].length()];
        Arrays.fill(candidates, true);
        int[] votes;
        int count = voters[0].length();
        while (count > 0) {
            votes = new int[voters[0].length()];
            for (int i = 0; i < voters.length; i++)
                for (int j = 0; j < voters[i].length(); j++)
                    if (candidates[voters[i].charAt(j) - '0']) {
                        votes[voters[i].charAt(j) - '0']++;
                        break;
                    }
            int max = 0;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < votes.length; i++)
                if (candidates[i]) {
                    max = Math.max(max, votes[i]);
                    min = Math.min(min, votes[i]);
                }
            if (max > voters.length / 2)
                for (int i = 0; i < votes.length; i++)
                    if (votes[i] == max)
                        return i;
            for (int i = 0; i < votes.length; i++)
                if (votes[i] == min) {
                    candidates[i] = false;
                    count--;
                }
        }
        return -1;
    }
}
