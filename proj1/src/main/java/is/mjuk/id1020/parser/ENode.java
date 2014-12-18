package is.mjuk.id1020.parser;

public class ENode implements ParserNode {
    public final static NodeType nodeType = NodeType.E;
    public TNode left;
    public OrderNode right;

    public void setL(TNode child)
    {
        this.left = child;
    }

    public void setR(OrderNode child)
    {
        this.right = child;
    }

    public ParserNode getParent()
    {
        return null;
    }

    public NodeType getType()
    {
        return ENode.nodeType;
    }

    public TNode traverseL()
    {
        return left;
    }

    public OrderNode traverseR()
    {
        return right;
    }

}
