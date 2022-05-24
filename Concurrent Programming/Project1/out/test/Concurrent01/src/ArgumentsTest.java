import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArgumentsTest {
    @Test(expected = AssertionError.class)
    public void testNegativePortNumber() {
        new Arguments(-1, "foo", 1);
    }

    @Test(expected = AssertionError.class)
    public void testTooLargePortNumber() {
        new Arguments(65537, "foo", 1);
    }

    @Test(expected = AssertionError.class)
    public void testNullDirectory() {
        new Arguments(8080, null, 1);
    }

    @Test(expected = AssertionError.class)
    public void testZeroLengthDirectory() {
        new Arguments(8080, "", 1);
    }

    @Test
    public void testGood() {
        Arguments arguments = new Arguments(8080, "/", 1);
        assertEquals(arguments.getPort(), 8080);
        assertEquals(arguments.getDirectory(), "/");
        assertEquals(arguments.getResponses(), 1);
    }
}