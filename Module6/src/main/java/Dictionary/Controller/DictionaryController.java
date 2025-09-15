package Dictionary.Controller;

import Dictionary.Model.Dictionary;
import Dictionary.View.DictionaryView;

public class DictionaryController {
    private Dictionary dictionary = new Dictionary();
    private DictionaryView view;

    public DictionaryController(DictionaryView view) {
        this.view = view;
    }

    public void addWord(String word, String definition) {
        this.dictionary.addWord(word, definition);
    }

    public String getDefinition(String kw) {
        return this.dictionary.getDefinition(kw);
    }
}
