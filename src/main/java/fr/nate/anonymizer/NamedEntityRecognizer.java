package fr.nate.anonymizer;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * A wrapper around the Stanford NER module. It adds a regex to identify
 * email addresses to complement its output.
 */
public class NamedEntityRecognizer {
    private String _classifierPath;
    private AbstractSequenceClassifier<CoreLabel> _classifier;
    private final Logger _logger = LogManager.getLogger(getClass());

    private static final String _emailRegexp = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)\\W*";

    /**
     * Loads a classifier from an archive file containing a pre-trained model.
     * @param path A path to a pre-trained classifier.
     */
    public NamedEntityRecognizer(String path) {
        _classifierPath = path;
    }

    /**
     * Tags a sentence with NER annotations.
     * @param str The sentence to be tagged.
     * @return The sentence tagged in TSV format.
     * @throws IOException If the classifiergst
     * could not be loaded.
     * @throws ClassNotFoundException If the classifier could not be loaded.
     */
    public String classify(String str) throws IOException, ClassNotFoundException{
        if (_classifier == null) {
            loadClassifier();
        }
        return convertEmailAddresses(_classifier.classifyToString(str, "tsv", false));
    }

    private String convertEmailAddresses(String str) {
        String[] words = str.split("\\n");
        for (int i = 0; i < words.length; i++) {
            int sepIdx = words[i].lastIndexOf('\t');
            if (sepIdx >= 0) {
                String form = words[i].substring(0, sepIdx);
                if (form.matches(_emailRegexp)) {
                    words[i] = form + "\tEMAIL";
                }
            }

        }
        return String.join("\n", words);
    }

    private void loadClassifier() throws IOException, ClassNotFoundException {
        try {
            _classifier = CRFClassifier.getClassifier(_classifierPath);
        } catch (Exception e) {
            _logger.error(e.getMessage());
            throw e;
        }
    }
}
