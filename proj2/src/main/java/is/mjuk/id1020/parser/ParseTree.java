package is.mjuk.id1020.parser;

/*
 * (1) E → T
 * (2) E → T orderby Property Direction
 * (3) T → word
 * (4) T → T T
 * Property → {relevance, popularity, occurrence}
 * Direction → {asc, desc}
 */

import is.mjuk.id1020.index.Dictionary;
import is.mjuk.id1020.index.EntryNode;
import is.mjuk.id1020.utils.QuickSort;
import se.kth.id1020.util.Document;

import java.util.List;

public class ParseTree {
    ENode root = new ENode();

    public ParseTree(String[] term)
    {
        if (term.length < 1)
            return;

        root.setL(new TNode());

        for (int i = 0; i < term.length; i++)
        {
            if (term[i].toLowerCase().equals("orderby"))
            {
                OrderNode order = new OrderNode();
                order.setOrder(term[++i]);
                order.setDesc(term[++i]);
                root.setR(order);
                break;
            }

            root.traverseL().insert(term[i]);
        }
    }

    public List<Document> documentList(Dictionary dictionary)
    {
        EntryNode tmpNode = new EntryNode("TEMPORARY");

        for (String word : root.traverseL().getChildren())
        {
            EntryNode in = dictionary.getIndex(word);

            if (in == null) {
                System.err.println("No word " + word + " found");
                continue;
            }

            tmpNode.insert(in);
        }

        if (root.traverseR() != null)
        {
            QuickSort qs = new QuickSort(tmpNode.getAppearances(),
                    root.traverseR().getOrder(), root.traverseR().getDesc());
            return qs.sort();
        } else {
            return tmpNode.getDocuments();
        }
    }
}
