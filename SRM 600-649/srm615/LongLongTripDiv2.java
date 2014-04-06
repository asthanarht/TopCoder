package srm615;

public class LongLongTripDiv2 {

	public String isAble(long D, int T, int B) {
		long k = (D - T) / (B - 1);
		if (k >= 0 && k <= T && D == T + (B - 1) * k)
			return "Possible";
		return "Impossible";
	}

}
