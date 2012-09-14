package srm304;

public class Conditional {
	public double probability(int nDice, int maxSide, int v, int theSum) {
		double[][][] dp = new double[nDice][theSum + 1][2];
		double p = 1.0 / maxSide;
		for (int s = 1; s <= maxSide; s++)
			if (s == v)
				dp[0][Math.min(theSum, s)][1] += p;
			else
				dp[0][Math.min(theSum, s)][0] += p;
		for (int i = 1; i < nDice; i++)
			for (int j = 1; j <= theSum; j++)
				for (int s = 1; s <= maxSide; s++)
					if (s == v)
						dp[i][Math.min(theSum, j + s)][1] += dp[i - 1][j][0]
								* p + dp[i - 1][j][1] * p;
					else {
						dp[i][Math.min(theSum, j + s)][0] += dp[i - 1][j][0]
								* p;
						dp[i][Math.min(theSum, j + s)][1] += dp[i - 1][j][1]
								* p;
					}
		double sum = 0;
		for (int i = 1; i <= theSum; i++)
			sum += dp[nDice - 1][i][1];
		return dp[nDice - 1][theSum][1] / sum;
	}
}
