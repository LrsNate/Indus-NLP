package fr.nate.anonymizer;

import org.apache.commons.cli.ParseException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Nate on 01/12/15.
 */
public class ArgumentParserTest {

    @Test
    public void theClsOptionShouldBeRequired() {
        try {
            ArgumentParser.parse(new String[] {});
            Assert.fail("No exception caught!");
        } catch (ParseException e) {}
    }
}
