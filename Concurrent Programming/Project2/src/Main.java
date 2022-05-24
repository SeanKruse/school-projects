import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.http.HttpHeaders;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Executors;

class Arguments {
    private final int port;
    private final String directory;
    private final int responses;
    private final int threadCount;

    Arguments(int port, String directory, int responses, int threadCount) {

        if (port < 0) {
            throw new AssertionError("Port Number is negative, not a valid port number");
        }

        if (port > 65535) {
            throw new AssertionError("Port number is too large, not a valid port number");
        }

        if (directory == null) {
            throw new AssertionError("Directory is null");
        }

        if (directory.length() == 0) {
            throw new AssertionError("The directory is empty");
        }
        if (threadCount == 0) {
            throw new AssertionError("There needs to be at least one thread");
        }

        this.port = port;
        this.directory = directory;
        this.responses = responses;
        this.threadCount = threadCount;
    }

    int getPort() {
        return port;
    }

    String getDirectory() {
        return directory;
    }

    int getResponses() {
        return responses;
    }

    int getThreadCount() {
        return threadCount;
    }
}

class MyHttpHandler implements HttpHandler {
    private final Arguments arguments;
    private static int count;

    MyHttpHandler(Arguments arguments) {
        this.arguments = arguments;
    }

    @Override
    public void handle(HttpExchange e) throws IOException {
        // Alex Sanford worked with me to create a dynamic way to show the pathing of
        // the hello.html file. I was super
        // close but couldn't figure out how to use .getDirectory() to pass in a
        // non-hardcoded pathing. I ended up removing
        // this implementation when implementing e.getRequestURI()
        synchronized (this) {
            // Dr. Beaty provided the solution outline in class
            try {
                FileInputStream fis = new FileInputStream(
                        "." + arguments.getDirectory() + e.getRequestURI().toString());
                fileFound(e, fis.available(), fis);
            } catch (FileNotFoundException FNFE) {
                fileNotFound(e);
            }
            // System.out.println("Threads Finished: " + Thread.currentThread().getName());
        }
    }

    private void fileNotFound(HttpExchange httpExchange) throws IOException {
        // this adaptation of the outline was provided by Hunter Holton using
        // sendReponseHeaders with the length of the byte array as the second parameter
        synchronized (this) {
            byte[] responses = "FileNotFound".getBytes();
            httpExchange.sendResponseHeaders(404, responses.length);
            OutputStream os = httpExchange.getResponseBody();
            os.write(responses);
            os.close();
        }
    }

    private void fileFound(HttpExchange httpExchange, long length, FileInputStream fis) throws IOException {

        synchronized (this) {
            byte[] responses = fis.readAllBytes();
            httpExchange.sendResponseHeaders(200, responses.length);
            OutputStream os = httpExchange.getResponseBody();
            os.write(responses);
            os.close();
            // System.out.println("Thread Starting: " + Thread.currentThread().getName());
        }
    }
}

public class Main {

    static Arguments parseArgs(String[] args) {
        int port = 1;
        int responses = 1;
        String directory = null;
        int threadCount = 1;

        // Alex Sanford helped me set up the conditionals for parsing through the args
        // array.
        for (int i = 0; i < args.length; i++) {
            if (args[i].equalsIgnoreCase("--port")) {
                // grab the next element after --port within the args array
                port = Integer.parseInt(args[i + 1]);
            }
            if (args[i].equalsIgnoreCase("--responses")) {
                // grab the next element after --responses within the args array
                responses = Integer.parseInt(args[i + 1]);
            }
            if (args[i].equalsIgnoreCase("--directory")) {
                // grab the next element after --directory within the args array
                directory = args[i + 1];
            }
            if (args[i].equalsIgnoreCase("--threads")) {
                // grab the next element after --threads within the args array
                threadCount = Integer.parseInt(args[i + 1]);
            }
            if (args[i].contains("--") && !(args[i].contains("--port") || args[i].contains("--responses")
                    || args[i].contains("--directory") || args[i].contains("--threads"))) {
                // make sure that the command line arguments contain the correct arguments
                throw new IllegalArgumentException("Doesn't contain the correct argument");
            }
        }

        if (port == 1) {
            throw new AssertionError("Doesn't contain a port");
        }

        // create a new Arguments object passing in the port, directory,and responses.
        Arguments parsedArguments = new Arguments(port, directory, responses, threadCount);

        return parsedArguments;
    }

    public static void main(String[] args) throws IOException {
        Arguments arguments = parseArgs(args);
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(arguments.getPort()), 0);
        httpServer.createContext("/", new MyHttpHandler(arguments));
        httpServer.setExecutor(Executors.newFixedThreadPool(arguments.getThreadCount()));
        httpServer.start();
    }
}
