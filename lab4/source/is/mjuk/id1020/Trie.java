package is.mjuk.id1020;

import edu.princeton.cs.introcs.StdOut;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class Trie {  
    private Node root = new Node('\0');
    private Node state = null;

    public Node getRoot()
    {
        return this.root;
    }

    public void put(String k) 
    {
        this.put(k.toCharArray());
    }

    public void put(char[] k)
    {
        Node current = this.root;

        for (char c : k)
        {
            current.addChild(c);
            current = current.getChild(c);
        }

        current.increment();
    }

    public int get(String k)
    {
        return this.get(k.toCharArray());
    }

    public int get(char[] k)
    {
        Node current = dig(k);

        if (current == null)
            return 0;
        else
            return current.getValue();
    }

    public int distinct(String k)
    {
        return this.distinct(k.toCharArray());
    }

    public int distinct(char[] k)
    {
        Node current = dig(k);
        int count = 0;

        if (current == null)
            return 0;

        if (current.getValue() > 0)
            count += 1;

        ArrayList<Node> children = current.getChildren();
        
        if (children != null)
            count += num_of_children(children);

        return count;
    }

    public IterateTrie iterator(String prefix)
    {
        return new IterateTrie(prefix, this);
    }

    public int count(String k)
    {
        return this.count(k.toCharArray());
    }

    public int count(char[] k)
    {
        Node current = dig(k);
        
        if (current == null)
            return 0;

        int count = 0;

        count += current.getValue();

        ArrayList<Node> children = current.getChildren();
        
        if (children != null)
            count += children_count(children);

        return count;
    }

    public Node dig(String k)
    {
        return this.dig(k.toCharArray());
    }

    public Node dig(char[] k)
    {
        Node current = this.root;

        for (char c : k)
        {
            current = current.getChild(c);
            if (current == null)
                return null;
        }

        return current;
    }

    private int children_count(ArrayList<Node> al)
    {
        int count = 0;

        for (Node c : al)
        {
            ArrayList<Node> children = c.getChildren();
            
            count += c.getValue();
            if (children != null)
                count += children_count(children);
        }

        return count;
    }

    private int num_of_children(ArrayList<Node> al)
    {
        int count = 0;

        for (Node c : al)
        {
            ArrayList<Node> children = c.getChildren();
            
            if (c.getValue() > 0)
                count += 1;
            if (children != null)
                count += num_of_children(children);
        }

        return count;
    }
}
