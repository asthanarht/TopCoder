public class PointDistance {

	public int[] findPoint(int x1, int y1, int x2, int y2) {
		for (int x3 = -100; x3 <= 100; x3++)
			for (int y3 = -100; y3 <= 100; y3++) {
				int d1 = (x3 - x1) * (x3 - x1) + (y3 - y1) * (y3 - y1);
				int d2 = (x3 - x2) * (x3 - x2) + (y3 - y2) * (y3 - y2);
				if (d1 != 0 && d2 != 0 && d1 > d2)
					return new int[] { x3, y3 };
			}
		return null;
	}

}
