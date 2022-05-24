import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.DigestInputStream;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.Scanner;

public class Sender {
	private static final int BUFFER_SIZE = 2048;
	
	//Message Digest
	public static byte[] md(String f) throws Exception {
    	BufferedInputStream file = new BufferedInputStream(new FileInputStream(f));
    	MessageDigest md = MessageDigest.getInstance("SHA-256");
    	DigestInputStream dis = new DigestInputStream(file, md);
    	int i;
    	byte[] buffer = new byte[BUFFER_SIZE];
    	do {
      		i = dis.read(buffer, 0, BUFFER_SIZE);
    	} while (i == BUFFER_SIZE);
    	md = dis.getMessageDigest();
    	dis.close();

    	byte[] hash = md.digest();
    	System.out.println("Do you want to invert the 1st byte in SHA256(M)? (Y or N)");
    	Scanner kb = new Scanner(System.in);
    	if (kb.nextLine().equalsIgnoreCase("y"))
    		hash[0] = (byte)~hash[0];

    	System.out.println("SHA256(M):");
    	for (int k=0, j=0; k<hash.length; k++, j++) {
      		System.out.format("%2X ", hash[k]) ;
      		if (j >= 15) {
        		System.out.println();
        		j=-1;
      		}
    	}
    	System.out.println();    

    	return hash;
	}
	
	public static void main(String[] args) throws Exception {
		
		// Read in Kxy
		File symKeyFile = new File("symmetric.key");
		BufferedReader symKeyReader = new BufferedReader(new InputStreamReader(new FileInputStream(symKeyFile), StandardCharsets.UTF_8));
		String symKey = symKeyReader.readLine();
		
		// Read in Ky+
		File pubKeyYFile = new File("YPublic.key");
		ObjectInputStream oisPubKeyY = new ObjectInputStream(new BufferedInputStream(new FileInputStream(pubKeyYFile)));
		BigInteger mod = (BigInteger)oisPubKeyY.readObject();
		BigInteger exponent = (BigInteger)oisPubKeyY.readObject();
		
		RSAPublicKeySpec spec = new RSAPublicKeySpec(mod, exponent);
		KeyFactory factory = KeyFactory.getInstance("RSA");
		PublicKey yPub = factory.generatePublic(spec);
		
		// Ask user for name of message file
		System.out.println("Enter the name of the message file:");
		Scanner kb = new Scanner(System.in);
		String filename = kb.nextLine();
		byte[] dd = md(filename);
		
		File sha = new File("message.dd");
		BufferedOutputStream bos1 = new BufferedOutputStream(new FileOutputStream(sha));
		bos1.write(dd);
		bos1.flush();
		
		byte [] iv = "1234567890123456".getBytes();
		Cipher cipher1 = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
		SecretKeySpec symmetricKey = new SecretKeySpec(symKey.getBytes(StandardCharsets.UTF_8), "AES");
		cipher1.init(Cipher.ENCRYPT_MODE, symmetricKey, new IvParameterSpec(iv));
		byte[] encryptedSha = cipher1.doFinal(dd);
		
		System.out.println("AESEnKxy(SHA256(M)):");
    	for (int k=0, j=0; k<encryptedSha.length; k++, j++) {
      		System.out.format("%2X ", encryptedSha[k]) ;
      		if (j >= 15) {
        		System.out.println();
        		j=-1;
      		}
    	}
    	System.out.println();
    	
		File msg_add = new File("message.add-msg");
		
		BufferedOutputStream bos2 = new BufferedOutputStream(new FileOutputStream(msg_add));
		bos2.write(encryptedSha);
		bos2.flush();
		
		File msg = new File(filename);
		BufferedInputStream bis1 = new BufferedInputStream(new FileInputStream(msg));
		byte[] message = new byte[BUFFER_SIZE];
		int i;
		do {
			i = bis1.read(message, 0, BUFFER_SIZE);
			if (i == BUFFER_SIZE) {
				bos2.write(message);
			}
			else {
				bos2.write(message, 0, i);
			}
			bos2.flush();
		} while (i == BUFFER_SIZE);
		
		Cipher cipher2 = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher2.init(Cipher.ENCRYPT_MODE, yPub);
		
    	// Save the resulting blocks of RSA ciphertext into a file named message.rsacipher
		File rsaMessage = new File("message.rsacipher");
		BufferedOutputStream bos3 = new BufferedOutputStream(new FileOutputStream(rsaMessage));
		
    	// Calculate the RSA Encryption of (AES-En Kxy(SHA256(M)) || M) using Ky+ by reading the file message.add-msg piece by piece 
		// (each piece is recommended to be 117 bytes)
		BufferedInputStream bis2 = new BufferedInputStream(new FileInputStream(msg_add));
		byte[] buffer = new byte[117];
		do {
			i = bis2.read(buffer, 0, 117);
			byte[] temp;
			if (i == 117) {
				temp = cipher2.doFinal(buffer);
			}
			else {
				temp = cipher2.doFinal(buffer, 0, i);
			}
			bos3.write(temp);
		} while (i == 117);
		
		symKeyReader.close();
		oisPubKeyY.close();
		bos1.close();
		bos2.close();
		bos3.close();
		bis1.close();
		bis2.close();
		kb.close();
	}
}
