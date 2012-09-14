package srm320;

public class ExtraordinarilyLarge {
	public String compare(String x, String y) {
		if (x.startsWith("0!"))
			x = "1" + x.substring(2);
		if (y.startsWith("0!"))
			y = "1" + y.substring(2);
		int n = (x.indexOf('!') == -1 ? 0 : x.length() - x.indexOf('!'));
		n = Math.min(n,
				(y.indexOf('!') == -1 ? 0 : y.length() - y.indexOf('!')));
		x = x.substring(0, x.length() - n);
		y = y.substring(0, y.length() - n);
		if (x.indexOf('!') == -1 && y.indexOf('!') == -1) {
			int a = Integer.parseInt(x);
			int b = Integer.parseInt(y);
			if (a < b)
				return "x<y";
			if (a == b)
				return "x=y";
			return "x>y";
		}
		boolean swap = false;
		if (x.indexOf('!') != -1) {
			String tmp = x;
			x = y;
			y = tmp;
			swap = true;
		}
		long a = Integer.parseInt(x);
		long max = 0;
		long b = Integer.parseInt(y.substring(0, y.indexOf('!')));
		for (int i = 0; i < y.length() - y.indexOf('!'); i++) {
			max = b;
			b = 1;
			for (int num = 2; num <= max; num++) {
				b *= num;
				if (b > a)
					return swap ? "x>y" : "x<y";
			}
		}
		if (a == b)
			return "x=y";
		if (b > a)
			return swap ? "x>y" : "x<y";
		return swap ? "x<y" : "x>y";
	}
}
