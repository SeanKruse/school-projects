/* Copyrighted to Tomas Vilda
 * http://stilius.net/java/java_ssl.php */

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class EchoServer {
    public static void main(String[] arstring) {

        System.setProperty("javax.net.ssl.keyStore", "cs3750keystore");
        System.setProperty("javax.net.ssl.keyStorePassword", "password");


        try {
            SSLServerSocketFactory sslserversocketfactory =
                    (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            SSLServerSocket sslserversocket =
                    (SSLServerSocket) sslserversocketfactory.createServerSocket(9999);

          while (true) {

            SSLSocket sslsocket = (SSLSocket) sslserversocket.accept();

            InputStream inputstream = sslsocket.getInputStream();
            InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
            BufferedReader bufferedreader = new BufferedReader(inputstreamreader);

            String string = null;
            while ((string = bufferedreader.readLine()) != null) {
                System.out.println(string);
                System.out.flush();
		
		if (string.equals("Bye!"))
		    break;
            }

            bufferedreader.close();
            sslsocket.close();
          }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}

