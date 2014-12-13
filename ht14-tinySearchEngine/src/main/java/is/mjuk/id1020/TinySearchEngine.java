package is.mjuk.id1020;

import se.kth.id1020.TinySearchEngineBase;
import se.kth.id1020.util.Word;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import java.util.List;

class TinySearchEngine implements TinySearchEngineBase {
    private Index index = new Index();

	public void insert(Word word, Attributes attr)
	{
        this.index.insert(word, attr);
	}

	public List<Document> search(String query)
	{
		return null;
	}
}

