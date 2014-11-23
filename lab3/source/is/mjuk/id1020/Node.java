package is.mjuk.id1020;

import edu.princeton.cs.introcs.StdOut;

public class Node<T> {
    private Node nextNode = null;
    private Comparable<T> content = null;

    public Node(Comparable<T> element) {
        this.content = element;
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

    public Comparable<T> getContent() {
        return this.content;
    }

    public String toString() {
        try {
            return String.valueOf(this.content);
        } catch (Exception e) {
            StdOut.println(e.getMessage());
            return "";
        }
    }
}