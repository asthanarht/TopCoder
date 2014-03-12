package srm612;

public class EmoticonsDiv2 {
	public int printSmiles(int smiles) {
		int res = 0;
		for (int i = 2; i * i <= smiles; i++)
			if (smiles % i == 0) {
				int cnt = 0;
				while (smiles % i == 0) {
					smiles /= i;
					cnt++;
				}
				res += cnt * i;
			}
		if (smiles > 1)
			res += smiles;
		return res;
	}
}
