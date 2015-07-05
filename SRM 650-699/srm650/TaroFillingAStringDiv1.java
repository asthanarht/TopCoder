import java.util.Arrays;

public class TaroFillingAStringDiv1 {

	public int getNumber(int N, int[] position, String value) {
		long rst = 1, mod = 1000000007;
		int n = position.length;
		Letter[] given = new Letter[n];
		for (int i = 0; i < n; i++)
			given[i] = new Letter(position[i], value.charAt(i));
		Arrays.sort(given);
		for (int i = 0; i <= n; i++) {
			long start = (i == 0 ? 1 : given[i - 1].pos + 1);
			long end = (i == n ? N : given[i].pos - 1);
			if (start > end)
				continue;
			int a = 0, b = 0;
			if (start > 1) {
				if (given[i - 1].c == 'A')
					a++;
				else
					b++;
			}
			if (end < N) {
				if ((end + 1 - start) % 2 == 0) {
					if (given[i].c == 'A')
						b++;
					else
						a++;
				} else {
					if (given[i].c == 'A')
						a++;
					else
						b++;
				}
			}
			if (a == b) {
				rst *= (end - start + 2);
				rst %= mod;
			}
		}
		return (int) rst;
	}

}

class Letter implements Comparable<Letter> {
	long pos;
	char c;

	public Letter(long pos, char c) {
		super();
		this.pos = pos;
		this.c = c;
	}

	public int compareTo(Letter arg) {
		return pos < arg.pos ? -1 : 1;
	}
}
