## A multi-threaded web server in Java.
    The program creates a simple multi-threaded webserver that utilizes command line arguments to display a html file on a localhost.
    The command-line arguments are supplied for the web-server and can dictate how many threads are run concurrently.
    There are several junit tests to make sure that bad arguments, null directory, or fileNotFound errors are handled.

### Command-line arguments
* `--port <#>`: port number to listen on (e.g.: 8080)
* `--directory <dir>`: where to serve files from (e.g.: /tmp)
* `--responses <#>`: how many responses to make (0 = no limit)
* `--threads <#>`: how many threads to make (0 = no limit)

N.B.: one must use System.exit(0) instead of HttpServer.stop for JDKs > 8
because of bug https://bugs.openjdk.java.net/browse/JDK-8233185

Author: Sean Kruse

Github repo: https://github.com/msu-denver-cs/cs39acspring2020assignment02-xDazed

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
    java -cp out/production/Concurrent01 Main --port 8080 --responses 1 --directory /tmp --threads 10

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

### Performance Notes
    Apache Bench testing for concurrent threads with focus on three metrics: Requests per second, Time per request, and Transfer rate.
    
    Apache Bench Testing revealed a performance increase with each additonal thread from 1 thread to 11 threads using the three metrics of Requests per second, Time per request, and Transfer rate. 
    There was a performance drop off from Time per request starting at the introduction of 12 threads, but very little across all concurrent requests. 
    There was slight variation in running multiple tests with the same amount of threads, but generally speaking the trend was peak performance across all three metrics around 11 threads.
    
### Apache Bench perfomance with 1 thread:
    ab -n 1000 -c 1  http://localhost:8080/hello.html  
    This is ApacheBench, Version 2.3 <$Revision: 1879490 $>
    Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
    Licensed to The Apache Software Foundation, http://www.apache.org/

    Benchmarking localhost (be patient)
    Completed 100 requests
    Completed 200 requests
    Completed 300 requests
    Completed 400 requests
    Completed 500 requests
    Completed 600 requests
    Completed 700 requests
    Completed 800 requests
    Completed 900 requests
    Completed 1000 requests
    Finished 1000 requests


    Server Software:
    Server Hostname:        localhost
    Server Port:            8080

    Document Path:          /hello.html
    Document Length:        87 bytes

    Concurrency Level:      1
    Time taken for tests:   1.341 seconds
    Complete requests:      1000
    Failed requests:        0
    Total transferred:      182000 bytes
    HTML transferred:       87000 bytes
    Requests per second:    745.73 [#/sec] (mean)
    Time per request:       1.341 [ms] (mean)
    Time per request:       1.341 [ms] (mean, across all concurrent requests)
    Transfer rate:          132.54 [Kbytes/sec] received

    Connection Times (ms)
                  min  mean[+/-sd] median   max
    Connect:        0    0   0.4      0       1
    Processing:     0    1   0.4      1       2
    Waiting:        0    1   0.4      1       2
    Total:          0    1   0.5      1       3

    Percentage of the requests served within a certain time (ms)
      50%      1
      66%      1
      75%      2
      80%      2
      90%      2
      95%      2
      98%      2
      99%      2
     100%      3 (longest request)
     
### Apache Bench perfomance with 11 threads:
    ab -n 1000 -c 11  http://localhost:8080/hello.html 
    This is ApacheBench, Version 2.3 <$Revision: 1879490 $>
    Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
    Licensed to The Apache Software Foundation, http://www.apache.org/

    Benchmarking localhost (be patient)
    Completed 100 requests
    Completed 200 requests
    Completed 300 requests
    Completed 400 requests
    Completed 500 requests
    Completed 600 requests
    Completed 700 requests
    Completed 800 requests
    Completed 900 requests
    Completed 1000 requests
    Finished 1000 requests


    Server Software:
    Server Hostname:        localhost
    Server Port:            8080

    Document Path:          /hello.html
    Document Length:        87 bytes

    Concurrency Level:      11
    Time taken for tests:   0.461 seconds
    Complete requests:      1000
    Failed requests:        0
    Total transferred:      182000 bytes
    HTML transferred:       87000 bytes
    Requests per second:    2167.89 [#/sec] (mean)
    Time per request:       5.074 [ms] (mean)
    Time per request:       0.461 [ms] (mean, across all concurrent requests)
    Transfer rate:          385.31 [Kbytes/sec] received

    Connection Times (ms)
                  min  mean[+/-sd] median   max
    Connect:        0    0   0.4      0       1
    Processing:     1    5   2.9      4      16
    Waiting:        1    5   2.9      4      16
    Total:          1    5   2.9      4      16

    Percentage of the requests served within a certain time (ms)
      50%      4
      66%      6
      75%      7
      80%      8
      90%      9
      95%     10
      98%     12
      99%     12
     100%     16 (longest request)
     
 ### Apache Bench perfomance with 12 threads:
    ab -n 1000 -c 12  http://localhost:8080/hello.html 
    This is ApacheBench, Version 2.3 <$Revision: 1879490 $>
    Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
    Licensed to The Apache Software Foundation, http://www.apache.org/

    Benchmarking localhost (be patient)
    Completed 100 requests
    Completed 200 requests
    Completed 300 requests
    Completed 400 requests
    Completed 500 requests
    Completed 600 requests
    Completed 700 requests
    Completed 800 requests
    Completed 900 requests
    Completed 1000 requests
    Finished 1000 requests


    Server Software:
    Server Hostname:        localhost
    Server Port:            8080

    Document Path:          /hello.html
    Document Length:        87 bytes

    Concurrency Level:      12
    Time taken for tests:   0.462 seconds
    Complete requests:      1000
    Failed requests:        0
    Total transferred:      182000 bytes
    HTML transferred:       87000 bytes
    Requests per second:    2165.60 [#/sec] (mean)
    Time per request:       5.541 [ms] (mean)
    Time per request:       0.462 [ms] (mean, across all concurrent requests)
    Transfer rate:          384.90 [Kbytes/sec] received

    Connection Times (ms)
                  min  mean[+/-sd] median   max
    Connect:        0    0   0.4      0       1
    Processing:     1    5   3.2      5      17
    Waiting:        1    5   3.2      5      17
    Total:          1    5   3.2      5      17

    Percentage of the requests served within a certain time (ms)
      50%      5
      66%      7
      75%      8
      80%      8
      90%     10
      95%     11
      98%     13
      99%     14
     100%     17 (longest request)
