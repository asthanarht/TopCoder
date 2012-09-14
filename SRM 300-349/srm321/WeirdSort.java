package srm321;

import java.util.Arrays;
import java.util.LinkedList;

public class WeirdSort {
	public int[] sortArray(int[] data) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		Arrays.sort(data);
		boolean[] tick = new boolean[data.length];
		list.add(data[0]);
		for (int i = 1; i < data.length;)
			if (!tick[i]) {
				boolean done = false;
				for (int j = i; j < data.length; j++)
					if (!tick[j] && data[j] != list.get(list.size() - 1) + 1) {
						list.add(data[j]);
						done = true;
						tick[j] = true;
						break;
					}
				if (!done) {
					LinkedList<Integer> tmp = new LinkedList<Integer>();
					for (int j = list.size() - 1; j >= 0; j--) {
						int cur = list.removeLast();
						tmp.add(cur);
						if (data[i] + 1 != cur
								&& (list.size() == 0 || list
										.get(list.size() - 1) + 1 != data[i])) {
							list.add(data[i]);
							while (tmp.size() > 0)
								list.add(tmp.removeLast());
							tick[i] = true;
							break;
						}
					}
				}
			} else
				i++;
		int[] result = new int[list.size()];
		for (int i = 0; i < list.size(); i++)
			result[i] = list.get(i);
		return result;
	}
}
