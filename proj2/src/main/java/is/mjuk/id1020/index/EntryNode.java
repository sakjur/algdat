package is.mjuk.id1020.index;

import is.mjuk.id1020.utils.BinarySearch;
import is.mjuk.id1020.utils.Conversion;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import se.kth.id1020.util.Word;

import java.util.ArrayList;

/**
* Created by sakjur on 13/01/15.
*/
public class EntryNode
{
    private char[] label;
    private ArrayList<EntryData> appearances = new ArrayList<EntryData>();

    protected EntryNode left, right;
    protected int nodesInSubtree;
    protected boolean color;

    public EntryNode(Word key, int nodesInSubtree, boolean isRed) {
        this.nodesInSubtree = nodesInSubtree;
        this.color = isRed;
        this.label = key.word.toCharArray();
    }

    public EntryNode(String str, int nodesInSubtree, boolean isRed) {
        this.nodesInSubtree = nodesInSubtree;
        this.color = isRed;
        this.label = str.toCharArray();
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
        return Conversion.appearancesToDocument(this.getAppearances());
    }

    public String toString()
    {
        return String.valueOf(this.label);
    }
}
