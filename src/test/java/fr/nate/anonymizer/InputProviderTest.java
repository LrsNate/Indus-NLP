package fr.nate.anonymizer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

/**
 * Created by Nate on 24/12/15.
 */
public class InputProviderTest {

    private InputProvider _stdinProvider;

    @Before
    public void setUp() {
        _stdinProvider = new InputProvider(new String[0]);
    }

    @Test
    public void theStdinReaderShouldBeFetchable() {
        Assert.assertTrue(_stdinProvider.hasNextReader());
        Assert.assertNotNull(_stdinProvider.nextReader());
    }

    @Test
    public void theStdinReaderShouldBeUnique() {
        _stdinProvider.nextReader();
        Assert.assertFalse(_stdinProvider.hasNextReader());
        Assert.assertNull(_stdinProvider.nextReader());
    }
}
