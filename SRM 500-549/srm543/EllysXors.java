package srm543;

public class EllysXors {
	public long getXor(long L, long R) {
		if (L == R)
			return L;
		long result = (R - L + 1 + L % 2) % 4 / 2 % 2;
		for (long checker = (R >> 1), period = 2, i = 1; checker > 0; checker >>= 1, period <<= 1, i++) {
			long bl = (L >> i) % 2;
			long br = (R >> i) % 2;
			long cl, cr = (br == 0 ? 0 : R % period + 1), bit;
			if (R - L + 1 > period || bl == 0 || br == 0) {
				cl = (bl == 0 ? 0 : period - L % period);
				bit = (cl ^ cr) % 2;
			} else {
				cl = (bl == 0 ? 0 : L % period + 1);
				bit = (cr - cl > 1 ? (cr - cl - 1) % 2 : 0);
			}
			result += (bit << i);
		}
		return result;
	}
}