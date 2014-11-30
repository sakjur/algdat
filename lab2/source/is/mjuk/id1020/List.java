//
// 1) Linked list
// 2) Any other single way-traversable list of arbitrary data type
// 3) The worst case in my linked list is ith which has O(N)
// 4) My additional space complexity per element is 32, as the list contains
// 	two references and a overhead of 16 bytes.
//

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
		
			Node current = l.first();
			rv.append("(cons " + current.toString() + " nil)");

			while (current.hasNext())
			{
				current = current.getNext();
				rv.insert(0, " ");
				rv.insert(0, current.toString());
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
		if (this.firstNode != null) {
			this.firstNode = this.firstNode.getNext();
		}
		
		this.length = this.length - 1;

		return this.firstNode;
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

