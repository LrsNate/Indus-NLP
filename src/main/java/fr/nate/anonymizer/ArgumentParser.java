package fr.nate.anonymizer;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The command-line arguments parser. It relies on the Apache Commons
 * CLI library.
 */
public class ArgumentParser {

    private static Logger logger = LogManager.getLogger(ArgumentParser.class);

    /**
     * Factory method for the arguments parser.
     * @param args the args argument to the main method.
     * @return an instance of ArgumentParser.
     * @throws ParseException if the -c argument is missing.
     */
    public static ArgumentParser parse(String[] args) throws ParseException {
        logger.debug("Parsing arguments: [{}]", String.join(", ", args));
        Options o = new Options();
        o.addOption(Option.builder("c")
                .hasArg()
                .desc("A path to a classifier file")
                .required()
                .argName("cls")
                .build());
        o.addOption(Option.builder("h").desc("See this help page").build());
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(o, args);
            if (cmd.hasOption("h"))
                throw new ParseException("Showing help");
            String cl = cmd.getOptionValue("c");
            return new ArgumentParser(cl, cmd.getArgs());
        } catch (ParseException e) {
            HelpFormatter hf = new HelpFormatter();
            hf.printHelp("java -jar anonymizer.jar [-h] -c <cls> files...", o);
            throw e;
        }
    }

    private ArgumentParser(String cl, String[] files) {
        _classifier = cl;
        _files = files;
    }

    private String _classifier;
    private String[] _files;

    /**
     * Get the path to the classifier file.
     * A default value is provided if not found in the command line.
     * @return the path to the classifier file.
     */
    public String getClassifier() {
        return _classifier;
    }

    /**
     * Get a list of file paths to be processed by the anonymizer.
     * @return a list of file paths.
     */
    public String[] getFiles() {
        return _files;
    }
}
