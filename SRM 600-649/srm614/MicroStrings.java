package srm614;

public class MicroStrings {

	public String makeMicroString(int A, int D) {
		String res = "";
		while (A >= 0) {
			res += A;
			A -= D;
		}
		return res;
	}

}
