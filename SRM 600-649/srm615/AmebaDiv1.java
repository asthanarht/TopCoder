import java.util.HashSet;

public class AmebaDiv1 {

	public int count(int[] X) {
		HashSet<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < X.length; i++) {
			int n = X[i];
			for (int j = 0; j < X.length; j++)
				if (n == X[j])
					n += X[j];
			set.add(n);
		}
		HashSet<Integer> impossible = new HashSet<Integer>();
		for (int i = 0; i < X.length; i++)
			if (!set.contains(X[i]))
				impossible.add(X[i]);
		return impossible.size();
	}

}
