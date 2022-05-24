import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Scanner;

public class KeyGen {
	//saves public and private keys to files as bytes for binary operations
	public static void saveToFile(String fileName, BigInteger mod, BigInteger exp) throws IOException {
    	System.out.println("Write to " + fileName + ": modulus = " + 
        	mod.toString() + ", exponent = " + exp.toString() + "\n");
		
		try(ObjectOutputStream oosOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)))) {
			oosOut.writeObject(mod);
			oosOut.writeObject(exp);
		}
		catch(Exception e) {
			throw new IOException("Unexpected error", e);
		}
  	}
  	
 	public static void main(String[] args) throws Exception {
		//make a KeyPairGenerator
		SecureRandom random = new SecureRandom();
    	KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
    	keygen.initialize(1024, random);
    	
    	//generate two key pairs, one for X and one for Y
    	KeyPair pair1 = keygen.generateKeyPair();
    	KeyPair pair2 = keygen.generateKeyPair();
    	
    	//split the pairs between public and private for both X and Y
    	Key publicKeyX = pair1.getPublic();
    	Key privateKeyX = pair1.getPrivate();
    	Key publicKeyY = pair2.getPublic();
    	Key privateKeyY = pair2.getPrivate();

    	//grab the attributes of the four keys
    	KeyFactory factory = KeyFactory.getInstance("RSA");
    	RSAPublicKeySpec publicKSpecX = factory.getKeySpec(publicKeyX, RSAPublicKeySpec.class);
    	RSAPrivateKeySpec privateKSpecX = factory.getKeySpec(privateKeyX, RSAPrivateKeySpec.class);
    	RSAPublicKeySpec publicKSpecY = factory.getKeySpec(publicKeyY, RSAPublicKeySpec.class);
    	RSAPrivateKeySpec privateKSpecY = factory.getKeySpec(privateKeyY, RSAPrivateKeySpec.class);

    	//save the parameters of the keys to the files
    	saveToFile("XPublic.key", publicKSpecX.getModulus(), publicKSpecX.getPublicExponent());
    	saveToFile("XPrivate.key", privateKSpecX.getModulus(), privateKSpecX.getPrivateExponent());
    	saveToFile("YPublic.key", publicKSpecY.getModulus(), publicKSpecY.getPublicExponent());
    	saveToFile("YPrivate.key", privateKSpecY.getModulus(), privateKSpecY.getPrivateExponent());
    	
    	//save user string to text file for symmetric key
    	System.out.println("Enter a 16-character string for the symmetric key: ");
    	Scanner kb = new Scanner(System.in);
    	String kXY = kb.nextLine();
    	kb.close();
    	File symmetricKey = new File("symmetric.key");
    	OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(symmetricKey), StandardCharsets.UTF_8.newEncoder());
    	osw.write(kXY);
    	osw.close();
	}
}