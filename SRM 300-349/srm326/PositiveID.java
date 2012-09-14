package srm326;

import java.util.HashMap;
import java.util.HashSet;

public class PositiveID {
	public int maximumFacts(String[] suspects) {
		int n = suspects.length;
		HashMap<Integer, HashSet<String>> cha = new HashMap<Integer, HashSet<String>>();
		for (int i = 0; i < n; i++) {
			HashSet<String> set = new HashSet<String>();
			String[] info = suspects[i].split(",");
			for (int j = 0; j < info.length; j++)
				set.add(info[j]);
			cha.put(i, set);
		}
		int max = 0;
		for (int i = 0; i < n; i++)
			for (int j = i + 1; j < n; j++) {
				int count = 0;
				for (String chara : cha.get(i))
					if (cha.get(j).contains(chara))
						count++;
				max = Math.max(max, count);
			}
		return max;
	}
}
