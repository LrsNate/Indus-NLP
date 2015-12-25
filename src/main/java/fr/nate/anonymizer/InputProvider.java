package fr.nate.anonymizer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Created by Nate on 23/12/15.
 */
public class InputProvider {
    private final Logger _logger = LogManager.getLogger(getClass());
    private final LinkedList<String> _files = new LinkedList<>();
    private boolean _stdin;
    private boolean _wasCalled;

    public InputProvider(String[] files) {
        _wasCalled = false;
        if (files.length > 0) {
            for (String f : files) {
                _files.push(f);
            }
            _stdin = false;
        } else {
            _stdin = true;
        }
    }

    public boolean hasNextReader() {
        return (_stdin && !_wasCalled) || !_files.isEmpty();
    }

    public BufferedReader nextReader() throws NoSuchElementException {
        if (_stdin) {
            return getStdinReader();
        } else {
            return getFileReader();
        }
    }

    private BufferedReader getStdinReader() {
        if (!_wasCalled) {
            _wasCalled = true;
            _logger.debug("Opening a reader on stdin...");
            return new BufferedReader(new InputStreamReader(System.in));
        } else {
            return null;
        }
    }

    private BufferedReader getFileReader() {
        try {
            if (_files.isEmpty()) {
                return null;
            }
            String path = _files.removeFirst();
            _logger.debug("Opening a reader on file: {}", path);
            return new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            _logger.warn(e.getMessage());
            return getFileReader();
        }
    }
}
