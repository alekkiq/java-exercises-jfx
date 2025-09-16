package Dictionary.Model;

import java.util.HashMap;

public class Dictionary {
    HashMap<String, String> dictionary;

    public Dictionary() {
        this.dictionary = new HashMap<>();

        this.dictionary.put("test", "tää on testi");
    }

    public void addWord(String word, String definition) {
        this.dictionary.put(word, definition);
    }

    public String getDefinition(String word) {
        if (!this.dictionary.containsKey(word))
            return null;

        return this.dictionary.get(word);
    }

    public boolean containsKey(String key) {
        return this.dictionary.containsKey(key);
    }
}
