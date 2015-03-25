package cn.linqinli;

import java.util.*;
//import java.util.Map.Entry;

class Tree {
	private class Node {
		String name;
		Node parent;

		Node(String name) {
			this.name = name;
		}
		
	}

	private Map<String, Node> nodeMap = new HashMap<String, Node>();

	private Node getNodeByName(String name) {
		/*
		 * Set<Entry<String, Node>> nodeSet = nodeMap.entrySet();
		 * Iterator<Entry<String, Node>> it = nodeSet.iterator();
		 * while(it.hasNext()) { Entry<String, Node> entry = it.next();
		 * if(entry.getKey().equals(name)) return entry.getValue(); }
		 */
		Node n;
		if (nodeMap.containsKey(name)) {
			n = nodeMap.get(name);
		} else {
			n = new Node(name);
			nodeMap.put(name, n);
		}
		return n;
	}

	public void put(String parent, String child) {
		Node parentNode = getNodeByName(parent);
		Node childNode = getNodeByName(child);
		childNode.parent = parentNode;
	}

	public String lowestCommonAncestor(String parent, String child) {
		Node parentNode = getNodeByName(parent);
		Node childNode = getNodeByName(child);
		int countForChild = 0, countForParent = 0;
		Node currentNode = parentNode;
		while (currentNode.parent != null) {
			countForParent++;
			currentNode = currentNode.parent;
		}
		currentNode = childNode;
		while (currentNode.parent != null) {
			countForChild++;
			currentNode = currentNode.parent;
		}
		Node pChild = childNode, pParent = parentNode;
		while (countForChild != countForParent) {
			if (countForChild > countForParent) {
				pChild = pChild.parent;
				countForChild--;
			} else {
				pParent = pParent.parent;
				countForParent--;
			}
		}
		while (countForChild-- >= 0) {
			if (pChild.name.equals(pParent.name)) {
				return pChild.name;
			} else {
				pChild = pChild.parent;
				pParent = pParent.parent;
			}
		}
		return null;
	}
}

public class Solution {
	public static void main(String[] args) throws Exception {
		Scanner cin = new Scanner(System.in);
		while (cin.hasNext()) {
			Tree t = new Tree();
			int n = cin.nextInt();
			for (int i = 0; i < n; i++) {
				String father = cin.next();
				String son = cin.next();
				t.put(father, son);
			}
			int m = cin.nextInt();
			for (int i = 0; i < m; i++) {
				String parent = cin.next();
				String child = cin.next();
				String ans = t.lowestCommonAncestor(parent, child);
				if (ans == null)
					ans = "-1";
				System.out.println(ans);
			}
		}
	}
}