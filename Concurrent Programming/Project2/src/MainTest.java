import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainTest {
    @Test(expected = AssertionError.class)
    public void testNoDirectory() {
        String[] a = { "--port", "8080", "--threads", "10" };
        Main.parseArgs(a);
    }

    @Test(expected = AssertionError.class)
    public void testNoPort() {
        String[] a = { "--directory", "/tmp", "--threads", "10" };
        Main.parseArgs(a);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnknownArgument() {
        String[] a = { "--foo", "/bar" };
        Main.parseArgs(a);
    }

    @Test(expected = AssertionError.class)
    public void testNoThreads() {
        String[] a = { "--responses", "10" };
        Main.parseArgs(a);
    }

    // Christian Lopez and I discussed ideas for how we would write tests for
    // testGoodArguments.
    // We both talked about how we would need to expand the test to include every
    // combination of arguments so that we could cover certain situations
    @Test
    public void testGoodArguments() {
        String[] a = { "--port", "8080", "--directory", "/tmp", "--threads", "10" };
        Arguments arguments = Main.parseArgs(a);
        assertEquals(arguments.getPort(), 8080);
        assertEquals(arguments.getDirectory(), "/tmp");
        assertEquals(arguments.getThreadCount(), 10);

        String[] b = { "--port", "8080", "--threads", "10", "--directory", "/tmp" };
        arguments = Main.parseArgs(b);
        assertEquals(arguments.getPort(), 8080);
        assertEquals(arguments.getDirectory(), "/tmp");
        assertEquals(arguments.getThreadCount(), 10);

        String[] c = { "--directory", "/tmp", "--port", "8080", "--threads", "10" };
        arguments = Main.parseArgs(c);
        assertEquals(arguments.getPort(), 8080);
        assertEquals(arguments.getDirectory(), "/tmp");
        assertEquals(arguments.getThreadCount(), 10);

        String[] d = { "--directory", "/tmp", "--threads", "10", "--port", "8080" };
        arguments = Main.parseArgs(d);
        assertEquals(arguments.getPort(), 8080);
        assertEquals(arguments.getDirectory(), "/tmp");
        assertEquals(arguments.getThreadCount(), 10);

        String[] e = { "--threads", "10", "--directory", "/tmp", "--port", "8080" };
        arguments = Main.parseArgs(e);
        assertEquals(arguments.getPort(), 8080);
        assertEquals(arguments.getDirectory(), "/tmp");
        assertEquals(arguments.getThreadCount(), 10);

        String[] f = { "--threads", "10", "--port", "8080", "--directory", "/tmp" };
        arguments = Main.parseArgs(f);
        assertEquals(arguments.getPort(), 8080);
        assertEquals(arguments.getDirectory(), "/tmp");
        assertEquals(arguments.getThreadCount(), 10);
    }
}