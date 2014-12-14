package is.mjuk.id1020.index;

import is.mjuk.id1020.BinarySearch;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import se.kth.id1020.util.PartOfSpeech;
import se.kth.id1020.util.Word;

import java.util.ArrayList;
import java.util.List;

public class Index {
    ArrayList<IndexNode> words = new ArrayList<IndexNode>();

    public void insert(Word word, Attributes attr)
    {
        if (word.pos == PartOfSpeech.PUNCTUATION)
            return;

        IndexNode node = new IndexNode(word);

        node = BinarySearch.getOrInsert(word.word, words, node);

        node.insert(attr);
    }

    public ArrayList<IndexNode> getWords()
    {
        return this.words;
    }

    public IndexNode getIndex(String key)
    {
        ArrayList<IndexNode> wordList = this.getWords();
        int i = BinarySearch.search(key, wordList);
        IndexNode curr;

        try {
            curr = wordList.get(i);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(key + " not found.");
            return null;
        }

        return curr;
    }

    public List<Document> search(String key)
    {
        IndexNode curr = getIndex(key);

        if (curr == null)
            return null;

        return curr.getDocuments();
    }
}
