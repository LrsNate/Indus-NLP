package fr.nate.anonymizer;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Created by Nate on 25/11/15.
 */
public class Anonymizer {

    private static Logger logger = LogManager.getLogger(Anonymizer.class);

    public static void main(String[] args) {
        ArgumentParser argp = ArgumentParser.parse(args);
        logger.info("Loading classifier: {}", String.valueOf(argp.getClassifier()));
        logger.info("Using files: {}", String.join(", ", argp.getFiles()));
        try {
            AbstractSequenceClassifier<CoreLabel> classifier =
                    CRFClassifier.getClassifier(argp.getClassifier());
            logger.info(classifier.classifyToString("Barack Obama met his mother a few days ago."));
        } catch (Exception e) {
            System.out.println();
            logger.error("{}: {}", e.getClass().getName(), e.getMessage());
        }
    }

    private Anonymizer() {}
}
