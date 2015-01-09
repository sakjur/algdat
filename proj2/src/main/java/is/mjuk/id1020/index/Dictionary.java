package is.mjuk.id1020.index;

import is.mjuk.id1020.utils.BinarySearch;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import se.kth.id1020.util.PartOfSpeech;
import se.kth.id1020.util.Word;

import java.util.ArrayList;
import java.util.List;

public class Dictionary {
    ArrayList<EntryNode> words = new ArrayList<EntryNode>();

    public void insert(Word word, Attributes attr)
    {
        if (word.pos == PartOfSpeech.PUNCTUATION)
            return;

        EntryNode node = new EntryNode(word);

        node = BinarySearch.getOrInsert(word.word, words, node);

        node.insert(attr);
    }

    public ArrayList<EntryNode> getWords()
    {
        return this.words;
    }

    public EntryNode getIndex(String key)
    {
        ArrayList<EntryNode> wordList = this.getWords();
        int i = BinarySearch.search(key, wordList);
        EntryNode curr;

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
        EntryNode curr = getIndex(key);

        if (curr == null)
            return null;

        return curr.getDocuments();
    }
}
