public class LittleElephantAndString {
	public int getNumber(String A, String B) {
		int n = A.length();
		for (int start = 0; start < n; start++) {
			boolean[] check = new boolean[n];
			int iA = 0, iB = start;
			while (iB < n) {
				while (iA < n && A.charAt(iA) != B.charAt(iB))
					iA++;
				if (iA < n) {
					check[iA] = true;
					iB++;
					iA++;
				} else
					break;
			}
			if (iB == n) {
				boolean ok = true;
				for (int i = 0; i < start; i++) {
					char c = B.charAt(i);
					boolean found = false;
					for (int j = 0; j < n; j++)
						if (!check[j] && A.charAt(j) == c) {
							check[j] = true;
							found = true;
							break;
						}
					if (!found) {
						ok = false;
						break;
					}
				}
				if (ok)
					return start;
			}
		}
		return -1;
	}
}
