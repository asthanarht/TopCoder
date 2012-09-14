package srm330;

import java.util.HashMap;

public class PrefixFreeSubsets {
	public long cantPrefFreeSubsets(String[] words) {
		Node root = new Node();
		for (int i = 0; i < words.length; i++)
			root.add(words[i]);
		return root.count();
	}
}

class Node {
	boolean ok = false;
	HashMap<Character, Node> children = new HashMap<Character, Node>();

	public void add(String s) {
		if (s.length() == 0) {
			ok = true;
			return;
		}
		char c0 = s.charAt(0);
		Node node = children.get(c0);
		if (node == null) {
			node = new Node();
			children.put(c0, node);
		}
		node.add(s.substring(1));
	}

	public long count() {
		if (children.size() == 0)
			return 2;
		long count = 1;
		for (Node node : children.values())
			count *= node.count();
		if (ok)
			count++;
		return count;
	}
}
