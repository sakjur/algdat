package is.mjuk.id1020;

import is.mjuk.id1020.index.Dictionary;
import is.mjuk.id1020.parser.Lexer;
import is.mjuk.id1020.parser.ParseTree;
import se.kth.id1020.TinySearchEngineBase;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import se.kth.id1020.util.Word;

import java.util.List;

public class TinySearchEngine implements TinySearchEngineBase {
    Dictionary dictionary = new Dictionary();

    public List<Document> search(String key)
    {
        String[] term = Lexer.tokenize(key);
        try {
            ParseTree tree = new ParseTree(term);
            List<Document> rv = tree.documentList(dictionary);
            return rv;
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return null;
        }
        // return dictionary.search(key);
    }

    public void insert(Word word, Attributes attr)
    {
        this.dictionary.insert(word, attr);
    }
}
