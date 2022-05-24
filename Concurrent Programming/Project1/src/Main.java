import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.Executors;

class Arguments {
    private final int port;
    private final String directory;
    private final int responses;

    Arguments(int port, String directory, int responses) {

        if (port < 0) {
            throw new AssertionError("Port Number is negative, not a valid port number");
        }

        if (port > 65535) {
            throw new AssertionError("Port number is too large, not a valid port number");
        }

        if(directory == null) {
            throw new AssertionError("Directory is null");
        }

        if(directory.length() == 0) {
            throw new AssertionError("The directory is empty");
        }

        this.port = port;
        this.directory = directory;
        this.responses = responses;
    }

    int getPort() { return port; }
    String getDirectory() { return directory; }
    int getResponses() { return responses; }
}

class MyHttpHandler implements HttpHandler {
    private final Arguments arguments;
    private static int count;


    MyHttpHandler(Arguments arguments) {
        this.arguments = arguments;
    }

    @Override
    public void handle(HttpExchange e) throws IOException {

        //Alex Sanford worked with me to create a dynamic way to show the pathing of the hello.html file. I was super
        //close but couldn't figure out how to use .getDirectory() to pass in a non-hardcoded pathing

        String dirpath = System.getProperty("user.dir");
        File file = new File(dirpath + arguments.getDirectory());
        byte[] response = Files.readAllBytes(Paths.get(String.valueOf(file.listFiles()[0])));
        e.sendResponseHeaders(200, response.length);
        OutputStream os = e.getResponseBody();
        os.write(response);
        os.close();

        /* Several attempts to utilize hello.html file without hardcoding the path to pass into the byte array follow:
        readAllBytes seemed to be the culprit for requiring the full path, but I ran out of time while researching how
        to implement a relative path and pass into the byte array

        Path path = Paths.get("..\\tmp\\hello.html");
        File file = new File("hello.html");
        String path = System.getProperty("user.dir") + "\\tmp\\hello.html";
        File filePath = new File(new File("hello.html").getAbsolutePath());
        filePath.concat("C:\\Users\\seank\\IdeaProjects\\assignment-01-xDazed\\tmp\\");
        FileInputStream fis = new FileInputStream() */
    }

    private void fileNotFound(HttpExchange httpExchange) throws IOException {
        if (httpExchange.getRequestURI().equals(404)) {
            throw new AssertionError("File not Found");
        }
    }

    private void fileFound(HttpExchange httpExchange, long length, FileInputStream in) throws IOException {
        if (httpExchange.getRequestURI().equals(200)) {
            throw new AssertionError("File Found");
        }
    }
}

public class Main {

    static Arguments parseArgs(String[] args) {
        int port = 1;
        int responses = 1;
        String directory = null;

        //Alex Sanford helped me set up the conditionals for parsing through the args array.
        for(int i = 0; i < args.length; i++) {
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
            if (args[i].contains("--") && !(args[i].contains("--port") || args[i].contains("--responses") || args[i].contains("--directory"))) {
                throw new IllegalArgumentException("Doesn't contain the correct argument");
            }
        }

        if (port == 1) {
            throw new AssertionError("Doesn't contain a port");
        }

        //create a new Arguments object passing in the port, directory,and responses.
        Arguments parsedArguments = new Arguments(port, directory, responses);

        return parsedArguments;
    }

    public static void main(String[] args) throws IOException {
        //System.out.println(System.getProperty("user.dir"));

        Arguments arguments = parseArgs(args);
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(arguments.getPort()), 0);
        httpServer.createContext("/", new MyHttpHandler(arguments));
        httpServer.setExecutor(Executors.newSingleThreadExecutor());
        httpServer.start();
    }
}
