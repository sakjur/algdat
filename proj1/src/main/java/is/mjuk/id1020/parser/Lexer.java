package is.mjuk.id1020.parser;

public class Lexer {
    public static String[] tokenize(String key)
    {
        String[] rv = key.split("\\s+");

        return rv;
    }
}