package is.mjuk.id1020;

import is.mjuk.id1020.index.Index;
import is.mjuk.id1020.index.IndexData;
import is.mjuk.id1020.index.IndexNode;
import se.kth.id1020.TinySearchEngineBase;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import se.kth.id1020.util.Word;

import java.util.ArrayList;
import java.util.List;

public class TinySearchEngine implements TinySearchEngineBase {
    Index index = new Index();

    public List<Document> search(String key)
    {
        ArrayList<IndexNode> wordlist = index.getWords();
        int i = BinarySearch.search(key, wordlist);
        IndexNode curr;

        try {
            curr = wordlist.get(i);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(key + " not found.");
            return null;
        }

        ArrayList<Document> rv = new ArrayList<Document>();

        for (IndexData data : curr.getAppearances())
        {
            rv.add(data.getDocument());
        }

        return rv;
    }

    public void insert(Word word, Attributes attr)
    {
        this.index.insert(word, attr);
    }
}
