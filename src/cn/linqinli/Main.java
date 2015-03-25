package cn.linqinli;

import java.util.*;

public class Main {
	private static class Node {
		String addr, next;
		int value;
		
		Node(String addr, int value, String next) {
			this.addr = addr;
			this.value = value;
			this.next = next;
		}
	}
	private static Map<String, Node> nodeMap = new HashMap<String, Node>(16384);
	private static Stack<Node> nodeStack = new Stack<Node>();
	
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner cin = new Scanner(System.in);
		while(cin.hasNext()) {
			String start = cin.next();
			int num = Integer.parseInt(cin.next());
			int reversed = Integer.parseInt(cin.next());
			while(num-- > 0) {
				String address = cin.next();
				int value = Integer.parseInt(cin.next());
				String next = cin.next();
				nodeMap.put(address, new Node(address, value, next));
			}
			String nextNode = start;
			while(reversed-- > 0) {
				Node n = nodeMap.get(nextNode);
				nodeMap.remove(nextNode);
				nextNode = n.next;
				nodeStack.push(n);
			}
			Node current = null, previous = null, head = null;
			while(!nodeStack.empty()) {
				current = nodeStack.pop();
				if(previous != null) {
					previous.next = current.addr;
					nodeMap.put(previous.addr, previous);
				} else {
					head = current;
				}
				previous = current;
			}
			current.next = nextNode;
			nodeMap.put(current.addr, current);
			nextNode = head.addr;
			while(!nextNode.equals("-1")) {
				Node n = nodeMap.get(nextNode);
				nextNode = n.next;
				System.out.println(n.addr + " " + n.value + " " + n.next);
			}
		}
	}

}
