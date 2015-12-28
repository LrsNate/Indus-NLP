package fr.nate.anonymizer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

/**
 * The model used to anonymize named entities. It relies on simple
 * word-to-word replacement, and identifies using the lowercase form
 * of the tokens. Its input must be tab-separated values from the
 * Stanford NER classifier.
 */
public class EntityDictionary {

    private final HashMap<String, String> _entities = new HashMap<>();
    private final Logger _logger = LogManager.getLogger(getClass());

    /**
     * Anonymizes the sentence given in argument, and updates the
     * replacement model.
     * @param line A sentence in TSV format.
     * @return The sentence anonymized and cleaned of its NER tags.
     */
    public String anonymize(String line) {
        learnEntities(line);
        return cleanOutput(replaceEntities(line));
    }

    private void learnEntities(String line) {
        for (String word : line.split("\\n")) {
            int sepIdx = word.lastIndexOf('\t');
            if (sepIdx < 0) {
                continue;
            }
            String form = word.toLowerCase();
            String kind = word.substring(sepIdx + 1);
            if (!"O".equals(kind) && !_entities.containsKey(form)) {
                kind = kind + "_" + Integer.toString(_entities.size());
                _entities.put(form, kind);
            }
        }
    }

    private String replaceEntities(String line) {
        String[] words = line.split("\\n");
        for (int i = 0; i < words.length; i++) {
            String form = words[i].toLowerCase();
            if (_entities.containsKey(form)) {
                words[i] = _entities.get(form);
            }
        }
        return String.join("\n", words);
    }

    private String cleanOutput(String line) {
        String[] words = line.split("\\n");
        for (int i = 0; i < words.length; i++) {
            int sepIdx = words[i].lastIndexOf('\t');
            if (sepIdx >= 0) {
                words[i] = words[i].substring(0, sepIdx);
            }
        }
        return String.join(" ", words);
    }
}
