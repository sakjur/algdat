package is.mjuk.id1020;

import edu.princeton.cs.introcs.StdOut;
import java.util.Map.Entry;

public class Main {
    public static void main(String[] args) {
        Trie t = new Trie();
        String s = "Gatsby believed in the green light, the orgastic future ";
        s += "that year by year recedes before us It eluded us then but ";
        s += "that's no matter tomorrow we will run faster, stretch out our ";
        s += "arms farther And one fine morning ";
        s += "So we beat on, boats against the current, ";
        s += "borne back ceaselessly into the past.";

        String[] sa = s.split("\\s+");

        for (String word : sa)
        {
            t.put(word);
        }

        String testWord = "th";

        StdOut.println();

        StdOut.println("There are " + String.valueOf(t.distinct(testWord)) + 
            " distinct words that begins with " + testWord);

        StdOut.println("There are " + String.valueOf(t.count(testWord)) + 
            " words in the text that begins with " + testWord);

        IterateTrie it = t.iterator(testWord);
        Entry<String, Integer> kv = null;

        while (it.hasNext())
        {
            kv = it.next();
            StdOut.println(kv.getKey() + ": " + String.valueOf(kv.getValue()));
        } 
    }
}
