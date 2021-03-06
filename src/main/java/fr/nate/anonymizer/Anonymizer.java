package fr.nate.anonymizer;

import fr.nate.anonymizer.io.IoProvider;
import fr.nate.anonymizer.io.IoProviderFactory;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;

/**
 * The anonymizer program.
 */
public class Anonymizer {

    private static Logger logger = LogManager.getLogger(Anonymizer.class);

    /**
     * The main class for the anonymizer program.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        try {
            ArgumentParser argp = ArgumentParser.parse(args);
            IoProvider ip = IoProviderFactory.getProvider(argp.getFiles());
            EntityDictionary ed = new EntityDictionary();
            logger.info("Loading classifier: {}", String.valueOf(argp.getClassifier()));
            NamedEntityRecognizer ner = new NamedEntityRecognizer(argp.getClassifier());
            BufferedReader br;
            while ((br = ip.nextReader()) != null) {
                String line;
                while ((line = br.readLine()) != null) {
                    ip.writeReturn(ed.anonymize(ner.classify(line)));
                }
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
