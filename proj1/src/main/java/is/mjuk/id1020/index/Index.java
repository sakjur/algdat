package is.mjuk.id1020.index;

import is.mjuk.id1020.BinarySearch;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import se.kth.id1020.util.Word;

import java.util.ArrayList;

public class Index {
    ArrayList<IndexNode> words = new ArrayList<IndexNode>();

    public void insert(Word word, Attributes attr)
    {
        IndexNode node = new IndexNode(word.word);

        node = BinarySearch.getOrInsert(word.word, words, node);

        node.insert(attr);
    }

    public ArrayList<IndexNode> getWords()
    {
        return this.words;
    }
}
