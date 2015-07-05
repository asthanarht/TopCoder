import java.util.Arrays;

public class TaroFriends {

	public int getNumber(int[] coordinates, int X) {
		Arrays.sort(coordinates);
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < coordinates.length; i++) {
			int left = coordinates[0] + X, right = coordinates[i] + X;
			for (int j = i + 1; j < coordinates.length; j++) {
				left = Math.min(left, coordinates[j] - X);
				right = Math.max(right, coordinates[j] - X);
			}
			min = Math.min(min, right - left);
		}
		return min;
	}

}
