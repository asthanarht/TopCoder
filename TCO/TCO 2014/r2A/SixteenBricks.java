import java.util.Arrays;

public class SixteenBricks {

	public int maximumSurface(int[] height) {
		Arrays.sort(height);
		int num = (height[0] + height[1]) * 4
				+ (height[2] + height[3] + height[4] + height[5]) * 2;
		int res = (height[8] + height[9] + height[10] + height[11] + height[12]
				+ height[13] + height[14] + height[15])
				* 4 - num;
		return res + 16;
	}

}
