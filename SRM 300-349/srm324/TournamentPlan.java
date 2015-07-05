import java.util.Arrays;

public class TournamentPlan {
    public int getTravelDistance(int[] street, int[] avenue) {
        Arrays.sort(street);
        Arrays.sort(avenue);
        int x = street[street.length / 2], y = avenue[avenue.length / 2];
        int result = 0;
        for (int i = 0; i < street.length; i++)
            result += Math.abs(street[i] - x) + Math.abs(avenue[i] - y);
        return result;
    }
}
