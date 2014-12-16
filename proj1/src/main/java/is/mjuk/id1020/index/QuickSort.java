package is.mjuk.id1020.index;

import is.mjuk.id1020.utils.Swap;
import se.kth.id1020.util.Document;

import java.util.ArrayList;

public class QuickSort {
    boolean desc = true;
    OrderBy order = OrderBy.POPULARITY;
    ArrayList<IndexStore> appearances;

    public static enum OrderBy {
        RELEVANCE,
        POPULARITY,
        OCCURRENCE
    }

    public QuickSort(ArrayList<IndexStore> appearances)
    {
        this.appearances = new ArrayList<IndexStore>(appearances);
    }

    public QuickSort(ArrayList<IndexStore> appearances, OrderBy order, boolean desc)
    {
        this.appearances = new ArrayList<IndexStore>(appearances);
        this.order = order;
        this.desc = desc;
    }

    public ArrayList<Document> sort()
    {
        if (appearances.size() == 0)
            return null;

        // Collections.shuffle(appearances); // Not really necessary
        sort(0, appearances.size()-1);

        return EntryNode.apperancesToDocument(appearances);
    }

    public void sort(int lo, int hi)
    {
        if (hi <= lo) return;
        int pivot = partition(lo, hi);
        sort(lo, pivot-1);
        sort(pivot+1, hi);
    }

    public int partition(int lo, int hi)
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

    private boolean compare(IndexStore fst, IndexStore snd) {
        boolean rv = false;

        if (order == OrderBy.POPULARITY) {
            rv = fst.getDocument().popularity > snd.getDocument().popularity;
        } else if (order == OrderBy.RELEVANCE) {
            rv = fst.countOccurrences() > snd.countOccurrences();
        } else if (order == OrderBy.OCCURRENCE) {
            rv = fst.getOccurrences().get(0) < snd.getOccurrences().get(0);
        }

        if (desc)
            return rv;
        else
            return !rv;
    }


}
