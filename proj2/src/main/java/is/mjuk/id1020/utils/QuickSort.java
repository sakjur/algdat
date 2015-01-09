package is.mjuk.id1020.utils;

import is.mjuk.id1020.index.EntryData;
import is.mjuk.id1020.index.EntryNode;
import se.kth.id1020.util.Document;

import java.util.ArrayList;

public class QuickSort {
    boolean desc = true;
    OrderBy order = OrderBy.POPULARITY;
    ArrayList<EntryData> appearances;

    public static enum OrderBy {
        RELEVANCE,
        POPULARITY,
        OCCURRENCE
    }

    public QuickSort(ArrayList<EntryData> appearances)
    {
        this.appearances = new ArrayList<EntryData>(appearances);
    }

    public QuickSort(ArrayList<EntryData> appearances, OrderBy order, boolean desc)
    {
        this.appearances = new ArrayList<EntryData>(appearances);
        this.order = order;
        this.desc = desc;
    }

    public ArrayList<Document> sort()
    {
        if (appearances.size() == 0)
            return null;

        // Collections.shuffle(appearances); // Not really necessary
        sort(0, appearances.size()-1);

        return EntryNode.appearancesToDocument(appearances, desc);
    }

    public void sort(int lo, int hi)
    {
        if (hi <= lo) return;
        int pivot = partition(lo, hi);
        sort(lo, pivot-1);
        sort(pivot+1, hi);
    }

    public final int partition(int lo, int hi)
    {
        Swap.swap(appearances, lo, hi);

        for(int i = lo; i <= hi; i++)
        {
            if (this.compare(appearances.get(i), appearances.get(hi)))
            {
                Swap.swap(appearances, i, lo);
                lo++;
            }
        }

        Swap.swap(appearances, lo, hi);
        return lo;
    }

    public final boolean compare(EntryData fst, EntryData snd) {
        boolean rv = false;

        if (order == OrderBy.POPULARITY) {
            rv = fst.getDocument().popularity > snd.getDocument().popularity;
        } else if (order == OrderBy.RELEVANCE) {
            rv = fst.countOccurrences() > snd.countOccurrences();
        } else if (order == OrderBy.OCCURRENCE) {
            // rv = fst.getOccurrences().get(0) < snd.getOccurrences().get(0);
            rv = compareOccurrences(fst, snd);
        }

        return rv;
    }

    public final boolean compareOccurrences(EntryData fst, EntryData snd)
    {
        boolean rv = fst.getOccurrences().size() > snd.getOccurrences().size();
        int i = 0;

        while (i < fst.getOccurrences().size() && i < fst.getOccurrences().size())
        {
            if (fst.getOccurrences().get(i) == snd.getOccurrences().get(i))
                i++;
            else
            {
                rv = fst.getOccurrences().get(i) < snd.getOccurrences().get(i);
                break;
            }
        }

        return rv;
    }


}
