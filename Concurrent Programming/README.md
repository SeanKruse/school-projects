# Concurrent Programming

### Project 1 (Single-threaded web server in Java)
- The program creates a single-threaded webserver to display a html file on a localhost.

### Project 2 (Multi-threaded web server in Java)
 
- The program creates a simple multi-threaded webserver that utilizes command line arguments to display a html file on a localhost.
- The command-line arguments are supplied for the web-server and can dictate how many threads are run concurrently.
- There are several junit tests to make sure that bad arguments, null directory, or fileNotFound errors are handled.

### Project 3 (Multi-threaded web server in Scala)

- A simble web-server using scalatra that takes a file and returns it as a future.
- If you navigate to the incorrect URL, the program provides a 404 Error.
- The project currently contains a simple html file named hello.html that will be returned, but there are instructions for handling any html file that the user would like to provide.

