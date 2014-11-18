package is.mjuk.id1020;

import java.lang.StringBuilder;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class List {
	private Node firstNode = null;
	private int length = 0;

	public static void main(String[] args) {
		while (!StdIn.isEmpty()) {

			String input = StdIn.readString();
			input = input.trim();
			char[] inputList = input.toCharArray(); 

			List l = new List();
			for (char c : inputList)
			{
				l.prepend(c);
			}

			printList(l);
		}
	}

	public static void printList(List l) {
		if (l.length() > 0) {
			StringBuilder rv = new StringBuilder();
			rv.append("(cons " + l.first().toString() + " nil)");
		
			for (int i = 1; i < l.length(); i++) {
				rv.insert(0, " ");
				rv.insert(0, l.ith(i).toString());
				rv.insert(0, "(cons ");
				rv.append(")");
			}

			StdOut.println(rv.toString());
		} else {
			StdOut.println("(nil)");
		}
	}

	public void List() {
		firstNode = null;
	}

	public boolean empty() {
		return firstNode == null;	
	}

	public Node first() {
		if (!this.empty()) {
			return firstNode;
		} else {
			return null;
		}
	}

	public Node rest()
	{
		Node currNode = null;

		if (this.firstNode != null) {
			currNode = this.firstNode.getNext();
		}

		return currNode;
	}

	public void prepend(Object o) {
		Node newNode = new Node(o);
		newNode.setNext(this.firstNode);
		this.firstNode = newNode;
		this.length = this.length + 1;
	}

	public Node ith(Integer e) {
		int i = 0;
		Node curr = this.firstNode;

		while(e != i)
		{
			curr = curr.getNext();	
			i = i + 1;
			if (i >= this.length) {
				return null;
			}
		}

		return curr;
	}

	public int length() {
		return this.length;
	}

	public class Node {
		private Node nextNode = null;
		private Object content = null;

		public Node(Object o) {
			this.content = o;
		}

		public boolean hasNext() {
			return nextNode != null;
		}

		public Node getNext() {
			return this.nextNode;
		}

		public void setNext(Node nextNode) {
			this.nextNode = nextNode;
		}

		public Object getContent() {
			return this.content;
		}

		public String toString() {
			return String.valueOf(this.content);
		}
	}
}

