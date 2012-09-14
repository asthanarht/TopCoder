package srm324;
public class ProductBundling {
	public int howManyBundles(String[] data) {
		int n = data[0].length();
		String[] items = new String[n];
		for (int i = 0; i < n; i++) {
			String item = "";
			for (int j = 0; j < data.length; j++)
				item += data[j].charAt(i);
			items[i] = item;
		}
		boolean[] tick = new boolean[n];
		int count = 0;
		for (int i = 0; i < n; i++)
			if (!tick[i]) {
				count++;
				for (int j = i + 1; j < n; j++)
					if (items[i].compareTo(items[j]) == 0)
						tick[j] = true;
			}
		return count;
	}
}
