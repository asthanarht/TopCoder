import java.util.Arrays;

public class SplitStoneGame {

	public String winOrLose(int[] number) {
		Arrays.sort(number);
		if (number[number.length - 1] >= 2 && number.length >= 3
				&& number.length % 2 == 1)
			return "WIN";
		return "LOSE";
	}

}
