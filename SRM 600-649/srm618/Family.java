import java.util.Arrays;
import java.util.HashSet;

public class Family {

	public String isFamily(int[] parent1, int[] parent2) {
		int n = parent1.length;
		int[] nodes = new int[n];
		Arrays.fill(nodes, -1);
		for (int i = 0; i < n; i++)
			if (parent1[i] != -1) {
				nodes[parent1[i]] = 0;
				nodes[parent2[i]] = 0;
			}
		boolean valid = true;
		for (int i = 0; i < n && valid; i++)
			if (nodes[i] == 0) {
				boolean gender = true;
				HashSet<Integer> node = new HashSet<Integer>();
				node.add(i);
				nodes[i] = 1;
				while (node.size() > 0 && valid) {
					gender = !gender;
					HashSet<Integer> next = new HashSet<Integer>();
					for (int j = 0; j < n; j++)
						if (parent1[j] != -1) {
							if (node.contains(parent1[j])) {
								if (nodes[parent2[j]] == 0)
									next.add(parent2[j]);
								else if (nodes[parent2[j]] != (gender ? 1 : 2))
									valid = false;
							} else if (node.contains(parent2[j])) {
								if (nodes[parent1[j]] == 0)
									next.add(parent1[j]);
								else if (nodes[parent1[j]] != (gender ? 1 : 2))
									valid = false;
							}
						}
					for (int k : next)
						nodes[k] = (gender ? 1 : 2);
					node = next;
				}
			}
		return (valid ? "Possible" : "Impossible");
	}
}
