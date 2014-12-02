package is.mjuk.id1020;

import edu.princeton.cs.introcs.StdOut;

public class Main {
    public static void main(String[] args) {
        Trie t = new Trie();
        String s = "Det ar min gamla van demonen introduktion intro intro intron introduktionmatematik";
        String[] sa = s.split("\\s");

        for (String word : sa)
        {
            t.put(word);
        }
        
        StdOut.println(t.count("intro"));
        StdOut.println(t.distinct("intro"));
    }
}
