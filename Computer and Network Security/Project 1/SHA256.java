import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;

public class SHA256{
  private static int BUFFER_SIZE = 32 * 1024;

  public static void main(String[] args) throws Exception {
    System.out.println("test.txt: " + md("test.txt"));
  }

  public static String md(String f) throws Exception {
    BufferedInputStream file = new BufferedInputStream(new FileInputStream(f));
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    DigestInputStream in = new DigestInputStream(file, md);
    int i;
    byte[] buffer = new byte[BUFFER_SIZE];
    do {
      i = in.read(buffer, 0, BUFFER_SIZE);
    } while (i == BUFFER_SIZE);
    md = in.getMessageDigest();
    in.close();

    byte[] hash = md.digest();

    System.out.println("digit digest (hash value):");
    for (int k=0, j=0; k<hash.length; k++, j++) {
      System.out.format("%2X ", new Byte(hash[k])) ;
      if (j >= 15) {
        System.out.println("");
        j=-1;
      }
    }
    System.out.println("");    

    return new String(hash);
  }
}

