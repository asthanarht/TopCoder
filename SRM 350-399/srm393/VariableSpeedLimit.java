package srm393;

public class VariableSpeedLimit {
    public double journeyTime(int journeyLength, int[] speedLimit) {
        double count = 0;
        int sum = 0;
        int t = 0;
        for (int i = 0; i < speedLimit.length; i++)
            sum += speedLimit[i];
        while (journeyLength >= sum) {
            count += speedLimit.length;
            journeyLength -= sum;
        }
        while (journeyLength > 0) {
            if (speedLimit[t] < journeyLength) {
                journeyLength -= speedLimit[t];
                count += 1;
            }
            else {
                count += journeyLength / (double) speedLimit[t];
                journeyLength = 0;
            }
            t++;
            t %= speedLimit.length;
        }
        return count;
    }
}
