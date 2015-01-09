package is.mjuk.id1020.parser;

import java.util.ArrayList;

public class TNode implements ParserNode {
    public final static NodeType nodeType = NodeType.T;
    private ParserNode parent;
    private ArrayList<String> children = new ArrayList<String>();

    public TNode()
    {
        return;
    }

    public TNode(String child)
    {
        this.children.add(child);
    }

    public ParserNode getParent()
    {
        return this.parent;
    }

    public void setParent(ParserNode parent)
    {
        this.parent = parent;
    }

    public ArrayList<String> getChildren()
    {
        return this.children;
    }

    public NodeType getType()
    {
        return TNode.nodeType;
    }

    public void insert(String word)
    {
        children.add(word);
    }

}
