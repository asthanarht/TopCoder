package srm396;

public class DNAString {
    public int minChanges(int maxPeriod, String[] dna) {
        String space = "AGCT";
        String dnas = "";
        for (int i = 0; i < dna.length; i++)
            dnas += dna[i];
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= maxPeriod; i++) {
            int[][] count = new int[i][4];
            for (int j = 0; j < dnas.length(); j++)
                count[j % i][space.indexOf(dnas.charAt(j))]++;
            int sum = 0;
            for (int j = 0; j < i; j++) {
                int max = 0;
                for (int k = 0; k < 4; k++) {
                    sum += count[j][k];
                    if (count[j][k] > max)
                        max = count[j][k];
                }
                sum -= max;
            }
            if (sum < min)
                min = sum;
        }
        return min;
    }
}
