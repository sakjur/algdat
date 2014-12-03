package is.mjuk.id1020;

import edu.princeton.cs.introcs.In;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map.Entry;


public class Driver {
    public static void main(String[] args) {
        Trie t = new Trie();
        URL url = ClassLoader.getSystemResource("./kap1.txt"); 
        if (url != null) 
        {
            System.out.println("Reading from: " + url); 
        } else {
            System.out.println("Couldn’t find file: kap1.txt"); 
        }
        
        In input = new In(url); while (!input.isEmpty()) {   
            String line = input.readLine().trim();
            
            String[] words = line.split("(\\. )|:|,|;|!|\\?|( - )|--|(\' )| "); 

            String lastOfLine = words[words.length - 1];
            
            if (lastOfLine.endsWith(".")) 
            {
                words[words.length - 1] = lastOfLine.substring(0, lastOfLine.length() - 1);
            }

            for (String word : words) {
                String word2 = word.replaceAll("\"|\\(|\\)", "");

                if (word2.isEmpty()) {
                    continue; 
                }

                // System.out.println(word2);

                t.put(word2);
            } 
        }

        System.out.println("");

        commonFirstLetter(t);
        prefix(t);

        System.out.println("");

        System.out.println("Ten most common words (not in internal order):");
        for (String s : mostCommonWords(t))
        {
            System.out.println(s);
        }

        System.out.println("");

        System.out.println("Ten least common words (not in internal order):");
        for (String s : leastCommonWords(t))
        {
            System.out.println(s);
        }

    }

    public static String[] mostCommonWords(Trie t)
    {
        final int nth = 10;

        String[] rv = new String[nth];
        int[] vals = new int[nth];

        IterateTrie it = t.iterator("");
        Entry<String, Integer> kv = null;

        while (it.hasNext())
        {
            kv = it.next();
            int currVal = kv.getValue();
            for (int i = 0; i < nth; i++)
            {
                if (vals[i] == 0 || currVal > vals[i])
                {
                    rv[i] = kv.getKey();
                    vals[i] = currVal;
                    break;
                }
            }
        }

        return rv;
    }

    public static String[] leastCommonWords(Trie t)
    {
        final int nth = 10;

        String[] rv = new String[nth];
        int[] vals = new int[nth];

        IterateTrie it = t.iterator("");
        Entry<String, Integer> kv = null;

        while (it.hasNext())
        {
            kv = it.next();
            int currVal = kv.getValue();
            for (int i = 0; i < nth; i++)
            {
                if (vals[i] == 0 || currVal < vals[i])
                {
                    rv[i] = kv.getKey();
                    vals[i] = currVal;
                    break;
                }
            }
        }

        return rv;
    }

    public static void commonFirstLetter(Trie t)
    {
        String mostProminentLetter = "";
        int mostProminentLetterCount = 0;

        for (Node child : t.getRoot().getChildren())
        {
            if (t.distinct(String.valueOf(child.getContent())) 
                > mostProminentLetterCount)
            {
                mostProminentLetter = String.valueOf(child.getContent());
                mostProminentLetterCount = 
                    t.distinct(String.valueOf(child.getContent()));
            }
        }

        System.out.println("The most prominent first letter is " +
            mostProminentLetter + " with " + 
            String.valueOf(mostProminentLetterCount) + " distinct words");
    }

    public static void prefix(Trie t)
    {
        String prefixOfTwo = "";
        int highestFrequency = 0;

        for (Node fst : t.getRoot().getChildren())
        {
            ArrayList<Node> gc = fst.getChildren();

            if (gc == null)
                continue;

            for (Node snd : gc)
            {
                char[] prefix = {fst.getContent(), snd.getContent()};
                int tmp = t.count(prefix);

                if (tmp > highestFrequency)
                {
                    prefixOfTwo = String.valueOf(prefix);
                    highestFrequency = tmp;
                }
            }
        }

        System.out.println("The most used two letter prefix is " +
            prefixOfTwo + " with " + 
            String.valueOf(highestFrequency) + " occurences");
    }
}