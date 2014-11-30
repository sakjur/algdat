package is.mjuk.id1020;

import java.lang.StringBuilder;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class Main {
        public static void main(String[] args) {
        while (!StdIn.isEmpty()) {

            String input = StdIn.readString();
            input = input.trim();
            input = new StringBuilder(input).reverse().toString();
            char[] inputList = input.toCharArray();

            List<Character> l = new List();
            for (Character c : inputList)
            {
                l.prepend(c);
            }

            Integer inversions = l.inversions();

            Integer swaps = l.sort();
            printList(l);

            StdOut.println(inversions.toString() + " inversions & " + 
                swaps.toString() + " swaps.");

            if (inversions == swaps) {
                StdOut.println("Same amount of inversions and swaps");
            } else {
                StdOut.println("Not the same amount of inversions and swaps?");
            }
        }
    }

    public static void printList(List l) {
        if (l.length() > 0) {
            StringBuilder rv = new StringBuilder();
            StringBuilder tail = new StringBuilder();

            Node current = l.first();
            rv.append("(cons " + current.toString() + " ");
            tail.insert(0, ")");

            while (current.hasNext())
            {
                current = current.getNext();
                rv.append("(cons ");
                rv.append(current.toString());
                rv.append(" ");
                tail.insert(0, ")");
            }

            tail.insert(0, "nil");

            rv.append(tail);

            StdOut.println(rv.toString());
        } else {
            StdOut.println("(nil)");
        }
    }
}