public class LittleElephantAndDouble {
	public String getAnswer(int[] A) {
		int max = A[0];
		for (int i = 0; i < A.length; i++)
			max = Math.max(max, A[i]);
		for (int i = 0; i < A.length; i++)
			while (A[i] < max)
				A[i] *= 2;
		for (int i = 0; i < A.length; i++)
			if (A[i] != max)
				return "NO";
		return "YES";
	}
}
