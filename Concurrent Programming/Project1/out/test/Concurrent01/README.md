## A single-threaded web server in Java.

### Command-line arguments
* `--port <#>`: port number to listen on (e.g.: 8080)
* `--directory <dir>`: where to serve files from (e.g.: /tmp)
* `--responses <#>`: how many responses to make (0 = no limit)

N.B.: one must use System.exit(0) instead of HttpServer.stop for JDKs > 8
because of bug https://bugs.openjdk.java.net/browse/JDK-8233185

Change things from here down to reflect your development environment.

Author: Sean Kruse

Github repo: https://github.com/msu-denver-cs/assignment-01-xDazed

Java version: 11.0.4

JUnit version: 4.13.1

### Building
The overall form of the directories are from Intellij, but you don't need
to keep them. If you do want to keep the directory structure but not use
IntelliJ, here are the commands to compile the app and tests.

    javac -d out/production/Concurrent01 src/Main.java
    javac -d out/production/Concurrent01 -cp out/production/Concurrent01:lib/junit-4.13.1.jar:lib/hamcrest-core-1.3.jar src/ArgumentsTest.java
    javac -d out/production/Concurrent01 -cp out/production/Concurrent01:lib/junit-4.13.1.jar:lib/hamcrest-core-1.3.jar src/MainTest.java
    javac -d out/production/Concurrent01 -cp out/production/Concurrent01:lib/junit-4.13.1.jar:lib/hamcrest-core-1.3.jar src/MyHttpHandler.java

### Running
    java -cp out/production/Concurrent01 Main --port 8080 --responses 1 --directory /tmp

Create a /tmp/hello.html file that looks like:

    <html>
    <head>
        <title>Hello world!</title>
    <body>
        Hello world!
    </body>
    </html>

Point your browser at http://localhost:8080/hello.html

### Testing
    java -ea -cp out/production/Concurrent01:lib/junit-4.13.1.jar:lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore MainTest
    java -ea -cp out/production/Concurrent01:lib/junit-4.13.1.jar:lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore MyHttpHandlerTest
    java -ea -cp out/production/Concurrent01:lib/junit-4.13.1.jar:lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore ArgumentsTest

