/* 
Program by Alex Sanford and Sean Kruse
CS3750
*/

import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class InfoCollectionClient {
	public static void main(String[] args) {
		
		System.setProperty("javax.net.ssl.trustStore", "3750truststore");
		System.setProperty("javax.net.ssl.trustStorePassword", "password");
		System.setProperty("javax.net.ssl.trustStoreType", "PKCS12");
		
		try {
			Scanner kb = new Scanner(System.in);
			System.out.println("Enter ip/dns address: ");
			String serverLocation = kb.nextLine();
			System.out.println("Enter port number: ");
			int portNum = kb.nextInt();
			SSLSocketFactory sslsocketfactory = (SSLSocketFactory)SSLSocketFactory.getDefault();
			SSLSocket sslsocket = (SSLSocket)sslsocketfactory.createSocket(serverLocation, portNum);
			
			SSLSession session = sslsocket.getSession();
			System.out.println("Peer host is " +  session.getPeerHost());
			System.out.println("Cipher suite is " + session.getCipherSuite());
			System.out.println("Protocol is " + session.getProtocol());
			System.out.println("Session ID is " + Arrays.toString(session.getId()));
			System.out.println("The creation time of this session is " + session.getCreationTime());
			System.out.println("The last accessed time of this session is " + session.getLastAccessedTime());
			
			InputStream inputStreamUserInput = System.in;
			InputStreamReader inputStreamReaderUserInput = new InputStreamReader(inputStreamUserInput);
			BufferedReader bufferedReaderUserInput = new BufferedReader(inputStreamReaderUserInput);
			
			InputStream inputstream = sslsocket.getInputStream();
			InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
			BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
			
			OutputStream outputstream = sslsocket.getOutputStream();
			DataOutputStream dataoutputstream = new DataOutputStream(outputstream);
			
			String stringOne = ""; 
			String stringTwo = "";
			
			while (!(stringOne.equalsIgnoreCase("Add more users? (yes or any for no)") && !stringTwo.equalsIgnoreCase("yes"))) {
				stringOne = bufferedreader.readLine();
				System.out.println(stringOne);
				stringTwo = bufferedReaderUserInput.readLine();
				dataoutputstream.writeBytes(stringTwo + "\n");
			}
			
			System.out.println("Cya!");
			dataoutputstream.close();
			bufferedreader.close();
			bufferedReaderUserInput.close();
			kb.close();
			sslsocket.close();
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
	}
}

