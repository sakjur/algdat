package is.mjuk.id1020.index;

import is.mjuk.id1020.BinarySearch;
import se.kth.id1020.util.Document;

import java.util.ArrayList;
import java.util.List;

public class IndexData implements Comparable<Document>
{
    private Document document;
    private ArrayList<Integer> occurrences = new ArrayList<Integer>();

    public IndexData(Document document)
    {
        this.document = document;
    }

    public Document getDocument()
    {
        return this.document;
    }

    public List<Integer> getOccurrences()
    {
        return this.occurrences;
    }

    public int countOccurrences()
    {
        return this.occurrences.size();
    }

    public int compareTo(Document key)
    {
        return this.document.name.compareTo(key.name);
    }

    public void insert(Integer key)
    {
        BinarySearch.getOrInsert(key, occurrences, key);
    }

    public String toString()
    {
        return this.document.name + ": " + this.occurrences.toString();
    }
}
