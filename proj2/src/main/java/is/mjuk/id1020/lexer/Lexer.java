// Lexer should put what type the word is of

package is.mjuk.id1020.lexer;

public class Lexer {
    public static String[] tokenize(String key)
    {
        String[] rv = key.split("\\s+");

        return rv;
    }
}
