package is.mjuk.id1020.index;

import se.kth.id1020.util.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuickSort {
    boolean desc = true;
    OrderBy order = OrderBy.OCCURRENCE;
    ArrayList<IndexData> appearances;

    public enum OrderBy {
        RELEVANCE,
        POPULARITY,
        OCCURRENCE
    }

    public QuickSort(ArrayList<IndexData> appearances)
    {
        this.appearances = new ArrayList<IndexData>(appearances);
    }

    public ArrayList<Document> sort()
    {
        if (appearances.size() == 0)
            return null;

        // TODO: Build own shuffle algorithm or remove
        Collections.shuffle(appearances);
        sort(0, appearances.size()-1);

        return IndexNode.apperancesToDocument(appearances);
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
        swap(appearances, lo, hi);

        for(int i = lo; i <= hi; i++)
        {
            if (this.compare(appearances.get(i), appearances.get(hi)))
            {
                swap(appearances, i, lo);
                lo++;
            }
        }

        swap(appearances, lo, hi);
        return lo;
    }

    public boolean compare(IndexData fst, IndexData snd) {
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

    public static <T> void swap(List<T> li, int a, int b)
    {
        T tmp = li.get(a);
        li.set(a, li.get(b));
        li.set(b, tmp);
    }
}
