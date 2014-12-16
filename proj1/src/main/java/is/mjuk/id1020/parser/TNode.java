package is.mjuk.id1020.parser;

public class TNode implements ParserNode {
    public final static NodeType nodeType = NodeType.T;
    private ParserNode parent;
    private ParserNode children[] = new ParserNode[2];

    public TNode(ParserNode fst, ParserNode snd)
    {
        this.children[0] = fst;
        this.children[1] = snd;
    }

    public TNode(ParserNode child)
    {
        this.children[0] = child;
    }


    public ParserNode getParent()
    {
        return this.parent;
    }

    public NodeType getType()
    {
        return TNode.nodeType;
    }

    public ParserNode traverseL()
    {
        return this.children[0];
    }

    public ParserNode traverseR()
    {
        return this.children[1];
    }

    public boolean insertWord(String word)
    {
        return false;
    }
}
