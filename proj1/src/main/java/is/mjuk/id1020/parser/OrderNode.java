package is.mjuk.id1020.parser;

import is.mjuk.id1020.index.EntryNode;
import is.mjuk.id1020.index.QuickSort.OrderBy;

import java.util.Objects;

public class OrderNode implements ParserNode {
    public final static NodeType nodeType = NodeType.OrderBy;
    private ParserNode parent;
    private OrderBy order;
    private boolean desc;

    public ParserNode getParent()
    {
        return this.parent;
    }

    public NodeType getType()
    {
        return OrderNode.nodeType;
    }

    public ParserNode traverseL()
    {
        return null;
    }

    public ParserNode traverseR()
    {
        return null;
    }

    public OrderBy getOrder()
    {
        return this.order;
    }

    public boolean getDesc()
    {
        return this.desc;
    }

    public void setOrder(String order)
    {
        if (order.toLowerCase().equals("relevance"))
            this.order = OrderBy.RELEVANCE;
        else if (order.toLowerCase().equals("popularity"))
            this.order = OrderBy.POPULARITY;
        else if (order.toLowerCase().equals("occurrence"))
            this.order = OrderBy.OCCURRENCE;
        else
            throw new IllegalArgumentException("Property not relevance, popularity or occurrence");
    }

    public void setOrder(OrderBy order)
    {
        this.order = order;
    }

    public void setDesc(String key)
    {
        if (key.toLowerCase().equals("desc"))
            this.desc = true;
        else if (key.toLowerCase().equals("asc"))
            this.desc = false;
        else
            throw new IllegalArgumentException("Must be descending or ascending");
    }
}
