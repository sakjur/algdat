package is.mjuk.id1020.index;

import is.mjuk.id1020.BinarySearch;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import se.kth.id1020.util.Word;

import java.util.ArrayList;

public class IndexNode implements Comparable<String> {
    private String label;
    private ArrayList<IndexData> appearances = new ArrayList<IndexData>();

    public IndexNode(Word key)
    {
        this.label = key.word;
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

    public ArrayList<Document> getDocuments()
    {
        return apperancesToDocument(this.getAppearances());
    }

    public static ArrayList<Document> apperancesToDocument(ArrayList<IndexData> appearances)
    {
        ArrayList<Document> rv = new ArrayList<Document>();

        for (IndexData data : appearances)
        {
            rv.add(data.getDocument());
        }

        return rv;
    }

    public String toString()
    {
        return this.label;
    }
}
