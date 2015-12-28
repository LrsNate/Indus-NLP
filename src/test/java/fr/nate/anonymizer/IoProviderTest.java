package fr.nate.anonymizer;

import fr.nate.anonymizer.io.IoProvider;
import fr.nate.anonymizer.io.StdinProvider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Nate on 24/12/15.
 */
public class IoProviderTest {

    private IoProvider _stdinProvider;

    @Before
    public void setUp() {
        _stdinProvider = new StdinProvider();
    }

    @Test
    public void theStdinReaderShouldBeFetchable() {
        try {
            Assert.assertNotNull(_stdinProvider.nextReader());
        } catch (IOException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void theStdinReaderShouldBeUnique() {
        try {
            _stdinProvider.nextReader();
            Assert.assertNull(_stdinProvider.nextReader());
        } catch (IOException e) {
            Assert.fail(e.getMessage());
        }

    }
}
