package srm610;

public class DivideByZero {

	public int CountNumbers(int[] numbers) {
		boolean[] nums = new boolean[101];
		for (int i = 0; i < numbers.length; i++)
			nums[numbers[i]] = true;
		boolean found = false;
		do {
			found = false;
			for (int i = 100; i >= 2; i--)
				for (int j = i - 1; j >= 2; j--)
					if (nums[i] && nums[j])
						if (!nums[i / j]) {
							nums[i / j] = true;
							found = true;
						}
		} while (found);
		int cnt = 0;
		for (int i = 1; i <= 100; i++)
			if (nums[i])
				cnt++;
		return cnt;
	}

}
