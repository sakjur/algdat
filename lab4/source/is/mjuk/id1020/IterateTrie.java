package is.mjuk.id1020;

import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;
import java.util.Iterator;
import java.util.ArrayList;
import java.lang.StringBuilder;
import java.lang.UnsupportedOperationException;
import edu.princeton.cs.introcs.StdOut;


class IterateTrie implements Iterator<Entry<String, Integer>>
{
    private Trie t;
    private String prefix;
    private Node topNode;
    private int counter;
    private int currPos = 0;

    private String current = "";

    public IterateTrie(String prefix, Trie t)
    {
        this.t = t;
        this.prefix = prefix;

        this.topNode = t.dig(this.prefix);

        counter = t.distinct(prefix);
    }

    public boolean hasNext() {
        if (counter > 0)
            return true;
        else
            return false;
    }

    public Node getWord(String k)
    {
        return this.getWord(k.toCharArray());
    }

    public Node getWord(char[] k)
    {
        Node n = t.dig(k);
        
        if (n == null)
            return null;

        if (current.compareTo(n.word()) < 0 && n.getValue() != '\0')
            return n;

        return nextWord(n.getChildren());
    }

    private Node nextWord(ArrayList<Node> al)
    {
        Node rv = null;

        for (Node c : al)
        {
            ArrayList<Node> children = c.getChildren();
            
            if (c.getValue() > 0)
                if (current.compareTo(c.word()) < 0) 
                {
                    return c;
                }
            if (children != null)
                rv = nextWord(children);
                if (rv != null)
                    return rv;
        }

        return rv;
    }

    public Entry<String, Integer> next()
    {
        counter--;
        
        ArrayList<Node> children = topNode.getChildren();
        Entry<String, Integer> rv = null;
        Node c = this.getWord(this.prefix);

        current = c.word();
        
        if (c != null)
            rv = new SimpleEntry<String, Integer>(
                        current, 
                        c.getValue()
            );

        return rv;
    }

    public void remove()
    {
        throw new UnsupportedOperationException();
    }
}