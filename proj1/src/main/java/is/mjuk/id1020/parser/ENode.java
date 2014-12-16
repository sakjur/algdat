package is.mjuk.id1020.parser;

public class ENode implements ParserNode {
    public final static NodeType nodeType = NodeType.E;
    private ParserNode children[] = new ParserNode[2];

    public void setL(ParserNode child)
    {
        this.children[0] = child;
    }

    public void setR(ParserNode child)
    {
        this.children[1] = child;
    }

    public ParserNode getParent()
    {
        return null;
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

}
