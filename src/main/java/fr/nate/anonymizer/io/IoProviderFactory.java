package fr.nate.anonymizer.io;

/**
 * A Factory class used to create the adequate IoProvider
 * for a list of inputs. If that list is empty, it will
 * be a StdinProvider. If it contains paths, it will be
 * a FilesProvier.
 */
public class IoProviderFactory {

    private IoProviderFactory() {}

    /**
     * Creates an adequate IoProvider for the arguments list.
     * @param files A list of input file paths.
     * @return An IoProvider.
     */
    public static IoProvider getProvider(String[] files) {
        if (files.length > 0) {
            return new FilesProvider(files);
        } else {
            return new StdinProvider();
        }
    }
}
