/* copyrighted to Tomas Vilda. 
 * http://stilius.net/java/java_ssl.php */

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class EchoClient {
    public static void main(String[] arstring) {

        //Take one argument, which specifies the port number that the server listens to, and create the SSL Server Socket

        System.setProperty("javax.net.ssl.trustStore", "cs3750truststore");
        System.setProperty("javax.net.ssl.trustStorePassword", "password");

        try {
            SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket("cs3750a.msudenver.edu", 9999);
            SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket("localhost", 9999);

            InputStream inputstream = System.in;
            InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
            BufferedReader bufferedreader = new BufferedReader(inputstreamreader);

            OutputStream outputstream = sslsocket.getOutputStream();
            OutputStreamWriter outputstreamwriter = new OutputStreamWriter(outputstream);
            BufferedWriter bufferedwriter = new BufferedWriter(outputstreamwriter);

            String string = null;
            while ((string = bufferedreader.readLine()) != null) {
                bufferedwriter.write(string + '\n');
                bufferedwriter.flush();

                if (string.equals("Bye!"))
                    break;
            }

            bufferedwriter.close();
            sslsocket.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}

