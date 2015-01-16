package is.mjuk.id1020.index;

import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import se.kth.id1020.util.PartOfSpeech;
import se.kth.id1020.util.Word;

import java.util.List;

/**
 * Container for a sorted list of words
 * <p>
 * This is a container for storing words as a index sorted by the word's ASCII-alphabetical order.
 * The Dictionary class is implemented as a red-black tree as presented in Sedgewick's Algorithms 4e chapter 3.3
 */
public class Dictionary
{
    public static final boolean RED = true;
    public static final boolean BLACK = false;

    private EntryNode root;

    public static boolean isRed(EntryNode node) {
        if (node == null) return false;
        return node.color;
    }

    public static int size(EntryNode node) {
        if (node == null) return 0;
        return node.nodesInSubtree;
    }

    public static EntryNode rotateLeft(EntryNode node)
    {
        EntryNode subnode = node.right;
        node.right = subnode.left;
        subnode.left = node;
        updateAfterRot(node, subnode);
        return subnode;
    }

    public static EntryNode rotateRight(EntryNode node)
    {
        EntryNode subnode = node.left;
        node.left = subnode.right;
        subnode.right = node;
        updateAfterRot(node, subnode);
        return subnode;
    }

    private static void updateAfterRot(EntryNode node, EntryNode subnode) {
        subnode.color = node.color;
        node.color = RED;
        subnode.nodesInSubtree = node.nodesInSubtree;
        node.nodesInSubtree = 1 + size(node.left) + size(node.right);
    }

    public void insert(Word word, Attributes attr) {
        if (word.pos == PartOfSpeech.PUNCTUATION)
            return;

        this.root = insert(root, word, attr);
        this.root.color = BLACK;
    }

    public EntryNode insert(EntryNode node, Word word, Attributes attr) {
        if (node == null) {
            EntryNode newNode = new EntryNode(word, 1, RED);
            newNode.insert(attr);
            return newNode;
        }

        int cmp = node.compareTo(word.word);
        if      (cmp > 0) node.left  = insert(node.left, word, attr);
        else if (cmp < 0) node.right = insert(node.right, word, attr);
        else node.insert(attr);

        if (isRed(node.right) && !isRed(node.left)) node = rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left)) node = rotateRight(node);
        if (isRed(node.left) && isRed(node.right)) flipColors(node);

        node.nodesInSubtree = size(node.left) + size(node.right) + 1;
        return node;
    }

    public EntryNode getIndex(String key) {
        return getIndex(root, key);
    }

    public EntryNode getIndex(EntryNode node, String key) {
        if (node == null) return null;

        int cmp = node.compareTo(key);
        if      (cmp > 0) return getIndex(node.left, key);
        else if (cmp < 0) return getIndex(node.right, key);
        else return node;
    }

    public List<Document> search(String key)
    {
        EntryNode curr = getIndex(key);

        if (curr == null)
            return null;

        return curr.getDocuments();
    }

    private void flipColors(EntryNode node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }
}
