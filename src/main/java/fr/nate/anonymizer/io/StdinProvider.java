package fr.nate.anonymizer.io;

import java.io.*;

/**
 * An IoProvider based on standard input.
 * Its output return will be the standard input.
 */
public class StdinProvider implements IoProvider {

    private boolean _wasCalled;

    /**
     * Creates a new IoProvider based on Stdin.
     */
    public StdinProvider() {
        _wasCalled = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BufferedReader nextReader() {
        if (_wasCalled) {
            return null;
        } else {
            _wasCalled = true;
            return new BufferedReader(new InputStreamReader(System.in));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeReturn(String line) throws IOException {
        System.out.println(line);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void closeReturnWriter() throws IOException {
    }
}
