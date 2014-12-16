package is.mjuk.id1020.parser;

import is.mjuk.id1020.index.EntryNode;
import is.mjuk.id1020.index.QuickSort.OrderBy;

public class WordNode implements ParserNode {

    public static final NodeType nodeType = NodeType.Word;
    private TNode parent;
    private String word;

    public WordNode(String content)
    {
        this.word = content;
    }

    public TNode getParent()
    {
        return this.parent;
    }

    public void setParent(TNode parent)
    {
        this.parent = parent;
    }

    public String getContent()
    {
        return this.word;
    }

    public NodeType getType()
    {
        return WordNode.nodeType;
    }

    public WordNode traverseL()
    {
        return null;
    }

    public WordNode traverseR()
    {
        return null;
    }
}
