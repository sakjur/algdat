package is.mjuk.id1020;

import se.kth.id1020.util.Word;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import java.util.ArrayList;

public class Index {
    private ArrayList<Node> index = new ArrayList<Node>();

    public void insert(Word word, Attributes attr)
    {

    }

    private class Node
    {
        private ArrayList<Attributes> instances = new ArrayList<Attributes>();
        private String key;

        public Node(Word word, Attributes attr)
        {
            if (word == null || attr == null)
                throw new NullPointerException("word or attr not provided");

            this.key = word.word;
            this.addInstance(attr);
        }

        public String getKey()
        {
            return this.key;
        }

        public void addInstance(Attributes attr)
        {
            if (attr == null)
                throw new NullPointerException("attr may not be null");

            this.instances.add(attr);
        }

    }

}