package is.mjuk.id1020;

import se.kth.id1020.Driver;
import se.kth.id1020.TinySearchEngineBase;

public class App {
    public static void main(String[] argv) throws Exception
    {
        TinySearchEngineBase searchEngine = new TinySearchEngine();
        Driver.run(searchEngine);
    }
}
