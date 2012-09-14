package srm328;

public class LightsCube {
	public int[] count(int N, String[] lights) {
		int n = lights.length;
		int[] x = new int[n], y = new int[n], z = new int[n];
		for (int i = 0; i < n; i++) {
			String[] info = lights[i].split(" ");
			x[i] = Integer.parseInt(info[0]);
			y[i] = Integer.parseInt(info[1]);
			z[i] = Integer.parseInt(info[2]);
		}
		int[] res = new int[n];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				for (int k = 0; k < N; k++) {
					int min = Integer.MAX_VALUE, index = -1;
					for (int l = 0; l < n; l++) {
						int dis = Math.abs(i - x[l]) + Math.abs(j - y[l])
								+ Math.abs(k - z[l]);
						if (dis < min) {
							min = dis;
							index = l;
						}
					}
					res[index]++;
				}
		return res;
	}
}
