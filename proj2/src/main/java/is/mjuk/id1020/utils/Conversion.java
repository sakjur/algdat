package is.mjuk.id1020.utils;

import is.mjuk.id1020.index.EntryData;
import se.kth.id1020.util.Document;

import java.util.ArrayList;

/**
 * Created by sakjur on 13/01/15.
 */
public class Conversion {
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
}
