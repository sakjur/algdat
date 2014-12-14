package is.mjuk.id1020;

import is.mjuk.id1020.index.Index;
import is.mjuk.id1020.index.IndexNode;
import is.mjuk.id1020.index.QuickSort;
import se.kth.id1020.TinySearchEngineBase;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import se.kth.id1020.util.Word;

import java.util.List;

public class TinySearchEngine implements TinySearchEngineBase {
    Index index = new Index();

    public List<Document> search(String key)
    {
        IndexNode in = this.index.getIndex(key);

        if (in == null)
            return null;

        QuickSort qs = new QuickSort(in.getAppearances());
        return qs.sort();
    }

    public void insert(Word word, Attributes attr)
    {
        this.index.insert(word, attr);
    }
}
