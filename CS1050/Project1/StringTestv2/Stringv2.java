public class Stringv2 {

public static int numWords(String s) {
    int count = 0;
    String prev = " ";
    
    for (int i = 0; i < s.length(); i++) {
        String current = s.substring(i, i +1);

        
        if(prev.equals(" ") && !current.equals(" ")){
            count++;
        }
        
        prev = current;
    }
    return count;    
}
}
