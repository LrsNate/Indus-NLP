package fr.nate.anonymizer.io;

import java.io.*;

/**
 * Created by Nate on 28/12/15.
 */
public class StdinProvider implements IoProvider {

    private boolean _wasCalled;

    public StdinProvider() {
        _wasCalled = false;
    }

    @Override
    public BufferedReader nextReader() {
        if (_wasCalled) {
            return null;
        } else {
            _wasCalled = true;
            return new BufferedReader(new InputStreamReader(System.in));
        }
    }

    @Override
    public void writeReturn(String line) throws IOException {
        System.out.println(line);
    }

    @Override
    public void closeReturnWriter() throws IOException {
    }
}
