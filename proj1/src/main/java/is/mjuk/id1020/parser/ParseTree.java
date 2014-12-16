package is.mjuk.id1020.parser;

/*
 * (1) E → T
 * (2) E → T orderby Property Direction
 * (3) T → word
 * (4) T → T T
 * Property → {relevance, popularity, occurrence}
 * Direction → {asc, desc}
 */

public class ParseTree {
    ENode root = new ENode();

    public ParseTree(String[] term)
    {
        if (term.length < 1)
            return;

        root.setL(new TNode(term[0]));

        for (int i = 1; i < term.length; i++)
        {
            System.out.println(term[i]);
        }
    }
}
