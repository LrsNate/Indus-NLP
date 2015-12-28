package fr.nate.anonymizer.io;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Created by Nate on 23/12/15.
 */
public interface IoProvider {

    BufferedReader nextReader() throws IOException;

    void writeReturn(String line) throws IOException;

    void closeReturnWriter() throws IOException;

}
