import java.util.ArrayList;

public class MovingRooksDiv1 {

	public int[] move(int[] Y1, int[] Y2) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		int n = Y1.length;
		for (int i = 0; i < n; i++) {
			if (Y1[i] < Y2[i])
				return new int[] { -1 };
			for (int j = i + 1; j < n; j++)
				if (Y1[j] < Y1[i] && Y1[j] >= Y2[i]) {
					int temp = Y1[i];
					Y1[i] = Y1[j];
					Y1[j] = temp;
					if (res.size() < 2500) {
						res.add(i);
						res.add(j);
					}
				}
		}
		int[] ans = new int[res.size()];
		for (int i = 0; i < res.size(); i++)
			ans[i] = res.get(i);
		return ans;
	}

}
