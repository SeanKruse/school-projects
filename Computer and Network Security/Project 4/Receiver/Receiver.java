import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.DigestInputStream;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.spec.RSAPrivateKeySpec;
import java.util.Scanner;
import java.util.Arrays;

public class Receiver {

	private static final int BUFFER_SIZE = 2048;
	
	// Message Digest
	// Read the message M from the user input file (each piece is recommended to be a small multiple of 1024 bytes)
	// Calculate the SHA256 hash value (digital digest) of the entire message M
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

    	System.out.println("Locally-calculated SHA256(M):");
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

		// Use RSA and AES Decryptions to get SHA256 (M) and M,compare SHA256(M) with the locally calculated SHA256 hash of M, report hashing error if any and save M to a file
		// file message.rsacipher needs to be copied here from the directory sender

		// Read the information on the keys to be used in this program from the key files and generate Ky- and Kxy

		// Read in Kxy
		File symKeyFile = new File("symmetric.key");
		BufferedReader symKeyReader = new BufferedReader(new InputStreamReader(new FileInputStream(symKeyFile), StandardCharsets.UTF_8));
		String symKey = symKeyReader.readLine();

		// Read in Ky-
		File prvKeyYFile = new File("YPrivate.key");
		ObjectInputStream oisPrvKeyY = new ObjectInputStream(new BufferedInputStream(new FileInputStream(prvKeyYFile)));
		BigInteger mod = (BigInteger)oisPrvKeyY.readObject();
		BigInteger exponent = (BigInteger)oisPrvKeyY.readObject();
		
		RSAPrivateKeySpec spec = new RSAPrivateKeySpec(mod, exponent);
		KeyFactory factory = KeyFactory.getInstance("RSA");
		PrivateKey yPrv = factory.generatePrivate(spec);

		Cipher cipher1 = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher1.init(Cipher.DECRYPT_MODE, yPrv);

		byte [] iv = "1234567890123456".getBytes();
		Cipher cipher2 = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
		SecretKeySpec symmetricKey = new SecretKeySpec(symKey.getBytes(StandardCharsets.UTF_8), "AES");
		cipher2.init(Cipher.DECRYPT_MODE, symmetricKey, new IvParameterSpec(iv));

		// Display a prompt "Input the name of the message file:" (the resulting message M will be saved to this file at the end of the program)
		// Ask user for name of message file
		System.out.println("Enter the name of the message file:");
		Scanner kb = new Scanner(System.in);
		String filename = kb.nextLine();

		// Read the ciphertext C from the file message.rsacipher block by block (each block is recommended to be 128 bytes long if padding is used)
		// Calculate the RSA Decryption of C using Ky- block by block to get AES-En Kxy(SHA256(M) || M)
		// Save the resulting pieces into a file named message.add-msg
		int i;
		File msg_add = new File("message.add-msg");
		BufferedOutputStream bos1 = new BufferedOutputStream(new FileOutputStream(msg_add));

		File rsaMessage = new File("message.rsacipher");
		BufferedInputStream bis1 = new BufferedInputStream(new FileInputStream(rsaMessage));

		byte[] buffer = new byte[128];
		do {
			i = bis1.read(buffer, 0, 128);
			byte[] temp = cipher1.doFinal(buffer);
			if (i == 128) bos1.write(temp);
		} while (i == 128);
		
		bos1.close();

		// Read the first 32 bytes from the file message.add-msg to get the authentic digital digest AES-EN Kxy(SHA256(M))
		BufferedInputStream bis2 = new BufferedInputStream(new FileInputStream(msg_add));
		byte[] buffer2 = new byte[32];
		bis2.read(buffer2, 0, 32);

		// Copy the message M (the leftover bytes in the file message.add-msg) to the user input message file
		File fileMessage = new File(filename);
		BufferedOutputStream bos2 = new BufferedOutputStream(new FileOutputStream(fileMessage));

		byte[] buffer3 = new byte[BUFFER_SIZE];
		do{
			i = bis2.read(buffer3, 0, BUFFER_SIZE);
			if (i == BUFFER_SIZE) {
				bos2.write(buffer3);
				bos2.flush();
			}
			else {
				bos2.write(buffer3, 0, i);
			}
		}while(i == BUFFER_SIZE);
		
		bos2.close();
		
		// Calculate the AES Decryption of the authentic digital digest using Kxy to get the digital digest SHA256(M)

		byte[] sha = cipher2.doFinal(buffer2);
		byte[] hash = md(filename); 
    	
		// Save this digital digest into a file named message.dd
		File localSha = new File("message.dd");
		BufferedOutputStream bos3 = new BufferedOutputStream(new FileOutputStream(localSha));
		bos3.write(hash);
		bos3.flush();
		
		// Display it in Hexadecimal bytes
    	
		System.out.println("AES Decryption of SHA256(M):");
    	for (int k=0, j=0; k<sha.length; k++, j++) {
      		System.out.format("%2X ", sha[k]) ;
      		if (j >= 15) {
        		System.out.println();
        		j=-1;
      		}
    	}
		
		// Compare it with the digital digest obtained and saved into message.dd

		boolean compArrays = Arrays.equals(hash, sha);
         
		// Display whether the digital digest passes the authentication check
        if(compArrays)
        {
            System.out.println("\nThe digital digest passes the authentication check.");
        }
        else
        {
            System.out.println("\nThe digital digest does not pass the authentication check.");
        }
		
		symKeyReader.close();
		oisPrvKeyY.close();
		bos1.close();
		bos2.close();
		bos3.close();
		bis1.close();
		bis2.close();
		kb.close();
	}
}
