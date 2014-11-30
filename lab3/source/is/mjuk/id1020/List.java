//
// 1) Linked list
// 2) Any other single way-traversable list of arbitrary data type
// 3) The worst case in my linked list is ith which has O(N)
// 4) My additional space complexity per element is 32, as the list contains
//  two references and a overhead of 16 bytes.
//

package is.mjuk.id1020;

import edu.princeton.cs.introcs.StdOut;

public class List<T> {
    private Node firstNode = null;
    private int length = 0;

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

    public void prepend(Comparable<T> element) {
        Node<T> newNode = new Node(element);
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

    public int sort() {
        int ticker = this.length() - 2;
        boolean swapped = true;

        Node curr;
        Node next;
        Node prev;

        int counter = 0;

        while (ticker >= 0 && swapped == true) {
            swapped = false;
            curr = this.first();
            prev = null;

            for (int i = 0; i <= ticker; i++) {
                next = curr.getNext();
                
                if (next == null) break;

                // Replace > with >= to get a stable version?
                if (curr.getContent().compareTo(next.getContent()) > 0) {
                    swapped = true;
                    curr = swap(next, curr, prev);
                    counter++;
                }
                
                prev = curr;
                curr = curr.getNext();

                if (curr == null) break;
            }
            
            ticker = ticker - 1;
        }

        return counter;
    }

    private Node swap(Node lesser, Node greater, Node previous) {
        greater.setNext(lesser.getNext());
        lesser.setNext(greater);

        if (previous == null) {
            this.firstNode = lesser;
        } else {
            previous.setNext(lesser);
        }

        return lesser;
    }

    public int inversions() {
        Node curr = this.first();
        int count = 0;

        while(curr != null) {
            Node inner = curr.getNext();

            while (inner != null) {

                if (inner.getContent().compareTo(curr.getContent()) < 0) {
                    count++;
                    // StdOut.println("(" + curr.toString() + ", "
                    //    + inner.getContent() + ")");
                }

                inner = inner.getNext();
            }

            curr = curr.getNext();
        }

        return count;
    }
}
