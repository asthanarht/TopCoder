import java.util.Arrays;

public class EllysSortingTrimmer {

	public String getMin(String S, int L) {
		int n = S.length();
		String[] dp = new String[n + 1];
		dp[n] = S;
		for (int len = n; len >= L; len--)
			if (dp[len] != null) {
				String from = dp[len];
				for (int i = 0; i + L <= len; i++) {
					String newString = from.substring(0, i);
					char[] seg = from.substring(i, i + L).toCharArray();
					Arrays.sort(seg);
					for (int j = 0; j < L; j++)
						newString += seg[j];
					int index = newString.length();
					if (dp[index] == null || dp[index].compareTo(newString) > 0)
						dp[index] = newString;
				}
				if (dp[len].compareTo(from) != 0)
					len++;
			}
		String res = null;
		for (int len = n; len >= L; len--)
			if (res == null || res.compareTo(dp[len]) > 0)
				res = dp[len];
		return res;
	}

}
