package is.mjuk.id1020.index;

import is.mjuk.id1020.BinarySearch;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import se.kth.id1020.util.Word;

import java.util.ArrayList;

public class IndexNode implements Comparable<String> {
    private String label;
    private ArrayList<IndexData> appearances = new ArrayList<IndexData>();

    public IndexNode(String key)
    {
        this.label = key;
    }

    public void insert(Attributes attr)
    {
        Document doc = attr.document;
        IndexData data = new IndexData(doc);

        data = BinarySearch.getOrInsert(doc, appearances, data);

        data.insert(attr.occurrence);
    }

    public int compareTo(String key)
    {
        return this.label.compareTo(key);
    }

    public ArrayList<IndexData> getAppearances()
    {
        return this.appearances;
    }

    public String toString()
    {
        return this.label;
    }
}
