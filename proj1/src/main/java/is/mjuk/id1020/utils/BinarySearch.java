package is.mjuk.id1020.utils;

import java.util.ArrayList;

public class BinarySearch {
    public static <T, AT extends Comparable<T>> int search(T key, ArrayList<AT> array)
    {
        if (array.size() == 0)
            return -1;

        int hi = array.size();
        int lo = 0;
        int mid = Integer.MAX_VALUE;
        int cmp;

        while(lo < hi)
        {
            mid = lo + (hi - lo)/2;
            cmp = array.get(mid).compareTo(key);

            if (cmp > 0)
                hi = mid;
            else if (cmp < 0)
                lo = mid + 1;
            else
                return mid;
        }

        return (-mid - 1);
    }

    public static <T, AT extends Comparable<T>> AT getOrInsert(T key, ArrayList<AT> array, AT node)
    {
        if (array.size() == 0)
        {
            array.add(node);
            return node;
        }

        int pos = BinarySearch.search(key, array);
        if (pos < 0) {
            pos = -pos - 1;
            if (pos >= array.size())
                array.add(node);
            else if (array.get(pos).compareTo(key) > 0)
                array.add(pos, node);
            else
                array.add(pos + 1, node);
            return node;
        } else {
            return array.get(pos);
        }
    }

}
