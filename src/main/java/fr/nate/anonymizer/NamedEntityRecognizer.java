package fr.nate.anonymizer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Nate on 24/12/15.
 */
public class NamedEntityRecognizer {
    private String _classifierPath;
    private final Logger _logger = LogManager.getLogger(getClass());

    public NamedEntityRecognizer(String path) {
        _classifierPath = path;
    }

    public void classify(String str) {
        _logger.debug(_classifierPath);
    }
}
