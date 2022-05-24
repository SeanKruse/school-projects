/*
Program by Alex Sanford and Sean Kruse
CS3750
*/

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class InfoCollectionServer {
	static int userCount = 0;
	static Scanner kb = new Scanner(System.in);
	
	public static void openSocket() {
		
		try {
			System.out.println("Enter port number: ");
			int portNum = kb.nextInt();
			kb.nextLine();
			SSLServerSocketFactory sslserversocketfactory = (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
			SSLServerSocket sslserversocket = (SSLServerSocket)sslserversocketfactory.createServerSocket(portNum);
			runServer(sslserversocket);
		}
		
		catch(Exception exception) {
			exception.printStackTrace();
		}
	}	
	
	public static void runServer(SSLServerSocket sslserversocket) {
		try {
				SSLSocket sslsocket = (SSLSocket)sslserversocket.accept();
				SSLSession session = sslsocket.getSession();
				System.out.println("Peer host is " +  session.getPeerHost());
				System.out.println("Cipher suite is " + session.getCipherSuite());
				System.out.println("Protocol is " + session.getProtocol());
				System.out.println("Session ID is " + Arrays.toString(session.getId()));
				System.out.println("The creation time of this session is " + session.getCreationTime());
				System.out.println("The last accessed time of this session is " + session.getLastAccessedTime());
				
				InputStream inputstream = sslsocket.getInputStream();
				InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
				BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
				
				OutputStream outputstream = sslsocket.getOutputStream();
				PrintStream printstream = new PrintStream(outputstream);
				
				File newUser;
				FileWriter fw = null;
				String s;
				for (int i = 0; i < 6; i++) {
					if (i == 0) {
						userCount += 1;
						newUser = new File(userCount + ".txt");
						fw = new FileWriter(newUser);
						printstream.println("User Name:");
						s = bufferedreader.readLine();
						fw.write("User name: " + s + "\n");
						fw.flush();
					}
					
					if (i == 1) {
						printstream.println("Full Name:");
						s = bufferedreader.readLine();
						fw.write("Full name: " + s + "\n");
						fw.flush();
					}
					
					if (i == 2) {
						printstream.println("Address:");
						s = bufferedreader.readLine();
						fw.write("Address: " + s + "\n");
						fw.flush();
					}
					
					if (i == 3) { 
						printstream.println("Phone number:");
						s = bufferedreader.readLine();
						fw.write("Phone number: " + s + "\n");
						fw.flush();
					}
					
					if (i == 4) {
						printstream.println("Email address:");
						s = bufferedreader.readLine();
						fw.write("Email address: " + s + "\n");
						fw.flush();
					}
					
					if (i == 5) {
						printstream.println("Add more users? (yes or any for no)");
						s = bufferedreader.readLine();
						if (s.equalsIgnoreCase("yes")) {
							i = -1;
						}
						else {
							System.out.println("No more users to add.");
							break;
						}
					}
				}
				
				System.out.println("Would you like the server to terminate? y/n");
				String userChoice = kb.nextLine();
				if (userChoice.equalsIgnoreCase("y")) { 
					System.out.println("Terminating server!");
					bufferedreader.close();
					printstream.close();
					fw.close();
					sslsocket.close();
					kb.close();
					System.out.println("Server terminated.");
				}
				else {
					System.out.println("Server is still running and listening for a new connection.");
					userCount = 0;
					sslsocket.close();
					runServer(sslserversocket);
				}
		}

		catch(Exception exception) {
			exception.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.setProperty("javax.net.ssl.keyStore", "3750keystore");
		System.setProperty("javax.net.ssl.keyStorePassword", "password");
		System.setProperty("javax.net.ssl.keyStoreType", "PKCS12");
		openSocket();
	}
}
