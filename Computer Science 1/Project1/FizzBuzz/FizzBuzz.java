import java.util.ArrayList;
/**
 * Write a description of class FizzBuzzGame here.
 *
 * @Sean Kruse
 * @version1 - 10/08/2018
 */
public class FizzBuzz {

    /**
     * Constructor for objects of class FizzBuzz
     */
    public FizzBuzz(){
    public static void main (String[] args) {

    }

    public ArrayList<String> FizzBuzz(int start, int end) {
        ArrayList<String> ar= new ArrayList<String>();
        
        //While loop with two else if statements
        
        //Initilize int index to the start
        
        int index = start;
        
        //Check for specific remainder of 3 and 5 first, then continue to just 3 and just 5
        
        while(index <= end){
            if(index % 3 == 0 && index % 5 == 0) {
                System.out.println("FizzBuzz");
            }    
            else if(index % 3 == 0){
                System.out.println("Fizz"); 
            }
            else if(index % 5 == 0){
                System.out.println("Buzz");
            }
            else{System.out.println(index + ", ");
            }
            index++;
        }
        return ar;  
    }
}
 }