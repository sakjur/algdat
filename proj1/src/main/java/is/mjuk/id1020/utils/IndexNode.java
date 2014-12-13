package is.mjuk.id1020.utils;

import se.kth.id1020.util.Document;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class IndexNode {
    private String label;
    private ArrayList<IndexData> appearances;

    public IndexNode(String key)
    {
        this.label = key;
    }

    private class IndexData implements Comparable<Document>
    {
        private Document document;
        private ArrayList<Integer> occurences;

        public IndexData(Document document)
        {
            this.document = document;
        }

        public Document getDocument()
        {
            return this.document;
        }

        public List<Integer> getOccurences()
        {
            return this.occurences;
        }

        public int numOfOccurences()
        {
            return this.occurences.size();
        }

        public int compareTo(Document key)
        {
            return key.name.compareTo(this.document.name);
        }
    }
}
