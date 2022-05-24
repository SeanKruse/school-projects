import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpPrincipal;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;

class MyHttpExchange extends HttpExchange {
    private final ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ByteArrayOutputStream getBaos() { return baos; }
    private int rCode;
    int getrCode() { return rCode; }
    private long responseLength;
    long getResponseLength() { return responseLength; }
    Headers headers = new Headers();
    Headers getHeaders() { return headers; }

    @Override public Headers getRequestHeaders() { return null; }
    @Override public Headers getResponseHeaders() { return headers; }

    @Override
    public URI getRequestURI() {
        try {
            return new URI("http://example.com");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override public String getRequestMethod() { return null; }
    @Override public HttpContext getHttpContext() { return null; }
    @Override public void close() {}
    @Override public InputStream getRequestBody() { return null; }

    @Override
    public OutputStream getResponseBody() {
        return baos;
    }

    @Override
    public void sendResponseHeaders(int rCode, long responseLength) throws IOException {
        this.rCode = rCode;
        this.responseLength = responseLength;
    }

    @Override public InetSocketAddress getRemoteAddress() { return null; }
    @Override public int getResponseCode() { return 0; }
    @Override public InetSocketAddress getLocalAddress() { return null; }
    @Override public String getProtocol() { return null; }
    @Override public Object getAttribute(String name) { return null; }
    @Override public void setAttribute(String name, Object value) {}
    @Override public void setStreams(InputStream i, OutputStream o) {}
    @Override public HttpPrincipal getPrincipal() { return null; }
}

public class MyHttpHandlerTest {
    @Test(expected = AssertionError.class)
    public void testNullDirectory() {
        Arguments arguments = new Arguments(8080, null, 1);
        new MyHttpHandler(arguments);
    }

    @Test
    public void testURI() throws IOException {
        MyHttpExchange myHttpExchange = new MyHttpExchange();
        Arguments arguments = new Arguments(8080, "/tmp", 2);
        MyHttpHandler myHttpHandler = new MyHttpHandler(arguments);
        myHttpHandler.handle(myHttpExchange);
        assertEquals(myHttpExchange.getrCode(), 404);
        myHttpHandler.handle(myHttpExchange);
    }
}