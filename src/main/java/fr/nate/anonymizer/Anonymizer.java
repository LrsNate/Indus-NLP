package fr.nate.anonymizer;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by Nate on 25/11/15.
 */
public class Anonymizer {

    private static Logger logger = LogManager.getLogger(Anonymizer.class);

    public static void main(String[] args) {
        try {
            ArgumentParser argp = ArgumentParser.parse(args);
            InputProvider ip = new InputProvider(argp.getFiles());
            EntityDictionary ed = new EntityDictionary();
            logger.info("Loading classifier: {}", String.valueOf(argp.getClassifier()));
            NamedEntityRecognizer ner = new NamedEntityRecognizer(argp.getClassifier());
            BufferedReader br;
            while ((br = ip.nextReader()) != null) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(ed.anonymize(ner.classify(line)));
                }
                System.out.println();
            }
        } catch (ParseException e) {
            System.exit(-1);
        } catch (Exception e) {
            System.out.println();
            logger.error("{}: {}", e.getClass().getName(), e.getMessage());
        }
    }

    private Anonymizer() {}
}
