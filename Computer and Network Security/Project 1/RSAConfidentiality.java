import java.io.*;

import java.security.Key;
import java.security.PublicKey;
import java.security.PrivateKey;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.Security;

import java.security.KeyFactory;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.RSAPrivateKeySpec;

import java.math.BigInteger;

import javax.crypto.Cipher;

public class RSAConfidentiality {
  public static void main(String[] args) throws Exception {

    byte[] input = "012340123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF".getBytes();
    Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

    //Generate a pair of keys
    SecureRandom random = new SecureRandom();
    KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
    generator.initialize(1024, random);  //1024: key size in bits
    KeyPair pair = generator.generateKeyPair();
    Key pubKey = pair.getPublic();
    Key privKey = pair.getPrivate();

    /* first, encryption & decryption via the paired keys */
    cipher.init(Cipher.ENCRYPT_MODE, pubKey, random);

    byte[] cipherText = cipher.doFinal(input);

    System.out.println("cipherText: block size = " + cipher.getBlockSize());
    for (int i=0, j=0; i<cipherText.length; i++, j++) {
      System.out.format("%2X ", new Byte(cipherText[i])) ;
      if (j >= 15) {
        System.out.println("");
        j=-1;
      }
    }
    System.out.println("");

    cipher.init(Cipher.DECRYPT_MODE, privKey);
    byte[] plainText = cipher.doFinal(cipherText);
    System.out.println("plainText : " + new String(plainText) + "&\n");

    /* next, store the keys to files, read them back from files, 
       and then, encrypt & decrypt using the keys from files. */

    //get the parameters of the keys: modulus and exponet
    KeyFactory factory = KeyFactory.getInstance("RSA");
    RSAPublicKeySpec pubKSpec = factory.getKeySpec(pubKey, 
        RSAPublicKeySpec.class);
    RSAPrivateKeySpec privKSpec = factory.getKeySpec(privKey, 
        RSAPrivateKeySpec.class);

    //save the parameters of the keys to the files
    saveToFile("RSAPublic.key", pubKSpec.getModulus(), 
        pubKSpec.getPublicExponent());
    saveToFile("RSAPrivate.key", privKSpec.getModulus(), 
        privKSpec.getPrivateExponent());

    //read the keys back from the files
    PublicKey pubKey2 = readPubKeyFromFile("RSAPublic.key");
    PrivateKey privKey2 = readPrivKeyFromFile("RSAPrivate.key");

    //encrypt & decrypt using the keys from the files
    byte[] input2 = "Hello World! (using the keys from files)".getBytes();

    cipher.init(Cipher.ENCRYPT_MODE, pubKey2, random);

    byte[] cipherText2 = cipher.doFinal(input2);

    System.out.println("cipherText2:");
    for (int i=0, j=0; i<cipherText2.length; i++, j++) {
      System.out.format("%2X ", new Byte(cipherText2[i])) ;
      if (j >= 15) {
        System.out.println("");
        j=-1;
      }
    }
    System.out.println("");

    cipher.init(Cipher.DECRYPT_MODE, privKey2);
    byte[] plainText2 = cipher.doFinal(cipherText2);
    System.out.println("plainText2 : " + new String(plainText2) + "\n");

  }


  //save the prameters of the public and private keys to file
  public static void saveToFile(String fileName,
        BigInteger mod, BigInteger exp) throws IOException {

    System.out.println("Write to " + fileName + ": modulus = " + 
        mod.toString() + ", exponent = " + exp.toString() + "\n");

    ObjectOutputStream oout = new ObjectOutputStream(
      new BufferedOutputStream(new FileOutputStream(fileName)));

    try {
      oout.writeObject(mod);
      oout.writeObject(exp);
    } catch (Exception e) {
      throw new IOException("Unexpected error", e);
    } finally {
      oout.close();
    }
  }


  //read key parameters from a file and generate the public key 
  public static PublicKey readPubKeyFromFile(String keyFileName) 
      throws IOException {

    InputStream in = 
        RSAConfidentiality.class.getResourceAsStream(keyFileName);
    ObjectInputStream oin =
        new ObjectInputStream(new BufferedInputStream(in));

    try {
      BigInteger m = (BigInteger) oin.readObject();
      BigInteger e = (BigInteger) oin.readObject();

      System.out.println("Read from " + keyFileName + ": modulus = " + 
          m.toString() + ", exponent = " + e.toString() + "\n");

      RSAPublicKeySpec keySpec = new RSAPublicKeySpec(m, e);
      KeyFactory factory = KeyFactory.getInstance("RSA");
      PublicKey key = factory.generatePublic(keySpec);

      return key;
    } catch (Exception e) {
      throw new RuntimeException("Spurious serialisation error", e);
    } finally {
      oin.close();
    }
  }


  //read key parameters from a file and generate the private key 
  public static PrivateKey readPrivKeyFromFile(String keyFileName) 
      throws IOException {

    InputStream in = 
        RSAConfidentiality.class.getResourceAsStream(keyFileName);
    ObjectInputStream oin =
        new ObjectInputStream(new BufferedInputStream(in));

    try {
      BigInteger m = (BigInteger) oin.readObject();
      BigInteger e = (BigInteger) oin.readObject();

      System.out.println("Read from " + keyFileName + ": modulus = " + 
          m.toString() + ", exponent = " + e.toString() + "\n");

      RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(m, e);
      KeyFactory factory = KeyFactory.getInstance("RSA");
      PrivateKey key = factory.generatePrivate(keySpec);

      return key;
    } catch (Exception e) {
      throw new RuntimeException("Spurious serialisation error", e);
    } finally {
      oin.close();
    }
  }


}

