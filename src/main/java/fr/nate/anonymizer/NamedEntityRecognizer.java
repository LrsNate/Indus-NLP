package fr.nate.anonymizer;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Nate on 24/12/15.
 */
public class NamedEntityRecognizer {
    private String _classifierPath;
    private AbstractSequenceClassifier<CoreLabel> _classifier;
    private final Logger _logger = LogManager.getLogger(getClass());

    public NamedEntityRecognizer(String path) {
        _classifierPath = path;
    }

    public String classify(String str) throws IOException, ClassNotFoundException{
        if (_classifier == null) {
            loadClassifier();
        }
        return _classifier.classifyToString(str, "tsv", false);
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
