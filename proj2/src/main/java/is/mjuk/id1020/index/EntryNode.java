package is.mjuk.id1020.index;

import is.mjuk.id1020.utils.BinarySearch;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import se.kth.id1020.util.Word;

import java.util.ArrayList;

public class EntryNode implements Comparable<String> {
    private char[] label;
    private ArrayList<EntryData> appearances = new ArrayList<EntryData>();

    public EntryNode(Word key)
    {
        this.label = key.word.toCharArray();
    }

    public EntryNode(String key)
    {
        this.label = key.toCharArray();
    }

    public void insert(Attributes attr)
    {
        Document doc = attr.document;
        EntryData data = new EntryData(doc);

        data = BinarySearch.getOrInsert(doc, appearances, data);

        data.insert(attr.occurrence);
    }

    public void insert(EntryNode data) {
        for (EntryData entry : data.appearances)
        {
            Document doc = entry.getDocument();
            EntryData store = new EntryData(doc);

            store = BinarySearch.getOrInsert(doc, this.appearances, store);

            for (Integer i : entry.getOccurrences())
            {
                store.insert(i);
            }
        }
    }

    public int compareTo(String key)
    {
        return String.valueOf(this.label).compareTo(key);
    }

    public ArrayList<EntryData> getAppearances()
    {
        return this.appearances;
    }

    public ArrayList<Document> getDocuments()
    {
        return EntryNode.appearancesToDocument(this.getAppearances());
    }

    public static ArrayList<Document> appearancesToDocument(ArrayList<EntryData> appearances, boolean desc)
    {
        ArrayList<Document> rv = new ArrayList<Document>();

        if (desc)
            for (int i = 0; i < appearances.size(); i++)
            {
                EntryData data = appearances.get(i);
                rv.add(data.getDocument());
            }
        else
            for (int i = appearances.size() - 1; i >= 0; i--)
            {
                EntryData data = appearances.get(i);
                rv.add(data.getDocument());
            }

        return rv;
    }

    public static ArrayList<Document> appearancesToDocument(ArrayList<EntryData> appearances)
    {
        return appearancesToDocument(appearances, true);
    }

    public String toString()
    {
        return String.valueOf(this.label);
    }
}
