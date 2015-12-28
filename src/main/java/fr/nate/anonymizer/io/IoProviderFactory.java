package fr.nate.anonymizer.io;

/**
 * Created by Nate on 28/12/15.
 */
public class IoProviderFactory {

    private IoProviderFactory() {}

    public static IoProvider getProvider(String[] files) {
        if (files.length > 0) {
            return new FilesProvider(files);
        } else {
            return new StdinProvider();
        }
    }
}
