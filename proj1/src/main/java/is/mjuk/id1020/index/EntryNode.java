package is.mjuk.id1020.index;

import is.mjuk.id1020.utils.BinarySearch;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import se.kth.id1020.util.Word;

import java.util.ArrayList;

public class EntryNode implements Comparable<String> {
    private String label;
    private ArrayList<IndexStore> appearances = new ArrayList<IndexStore>();

    public EntryNode(Word key)
    {
        this.label = key.word;
    }

    public EntryNode(String key)
    {
        this.label = key;
    }

    public void insert(Attributes attr)
    {
        Document doc = attr.document;
        IndexStore data = new IndexStore(doc);

        data = BinarySearch.getOrInsert(doc, appearances, data);

        data.insert(attr.occurrence);
    }

    public int compareTo(String key)
    {
        return this.label.compareTo(key);
    }

    public ArrayList<IndexStore> getAppearances()
    {
        return this.appearances;
    }

    public ArrayList<Document> getDocuments()
    {
        return apperancesToDocument(this.getAppearances());
    }

    public static ArrayList<Document> apperancesToDocument(ArrayList<IndexStore> appearances)
    {
        ArrayList<Document> rv = new ArrayList<Document>();

        for (IndexStore data : appearances)
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
