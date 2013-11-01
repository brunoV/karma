package me.karma.params;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class PositiveIntParamTest {

    @Parameter(value = 0)
    public String validInput;

    @Parameter(value = 1)
    public String invalidInput;

    @Parameters
    public static Collection<Object[]> inputs() {
        // It's kind of bad that we need to have the same number of valid inputs
        // as invalid inputs.
        // From what I've seen, I cannot have two independent sources of data
        // running on the same test class, the same way it can be done in TestNG.
        return Arrays.asList(new Object[][]{
                {"1", "0"}, {"24", "-1"}, {"10", "food"}, {"100231", "2.3"}, {"42", null}
        });
    }

    @Test(expected = RuntimeException.class)
    public void testInvalid() throws Exception {
        new PositiveIntParam(invalidInput);
    }

    @Test
    public void testValid() {
        new PositiveIntParam(validInput);
    }
}
