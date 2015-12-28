package fr.nate.anonymizer.io;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.LinkedList;

/**
 * Created by Nate on 28/12/15.
 */
public class FilesProvider implements IoProvider {

    private final LinkedList<String> _files;
    private final Logger _logger;
    private BufferedWriter _returnWriter;

    public FilesProvider(String[] files) {
        _files = new LinkedList<>();
        _logger = LogManager.getLogger(getClass());
        for (String f : files) {
            _files.addLast(f);
        }
        _returnWriter = null;
    }

    @Override
    public BufferedReader nextReader() throws IOException {
        closeReturnWriter();
        if (_files.isEmpty()) {
            return null;
        }
        try {
            String path = _files.removeFirst();
            _logger.debug("Opening a reader on file: {}", path);
            BufferedReader br = new BufferedReader(new FileReader(path));
            String returnPath = path + "_anon";
            _logger.debug("Output file: {}", returnPath);
            _returnWriter = new BufferedWriter(new FileWriter(returnPath));
            return br;
        } catch (FileNotFoundException e) {
            _logger.warn(e.getMessage());
            return nextReader();
        }
    }

    @Override
    public void writeReturn(String line) throws IOException {
        _returnWriter.write(line);
        _returnWriter.write("\n");
    }

    @Override
    public void closeReturnWriter() throws IOException {
        if (_returnWriter != null) {
            _returnWriter.close();
            _returnWriter = null;
        }
    }
}
