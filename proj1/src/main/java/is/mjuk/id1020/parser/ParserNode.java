package is.mjuk.id1020.parser;

import is.mjuk.id1020.index.EntryNode;

public interface ParserNode {
    public ParserNode traverseL();
    public ParserNode traverseR();
    public ParserNode getParent();
    public NodeType getType();
}
