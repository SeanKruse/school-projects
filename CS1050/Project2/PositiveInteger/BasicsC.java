// BasicsC.java
import java.util.ArrayList;
import java.util.List;
/**
 * Complete the source code for this class according to the following
 *     specifications.
 * 
 * 1. The @author tag below must have its value replaced with your name.
 * 
 * 2. Implement the methods in this class according to the specifications
 *    provided in the header comments for each method.
 * 
 * @author Sean Kruse
 * @version CS1050 Fall 2018
 */
public class BasicsC {
    /**
     * Returns the sum of all integers from 1 to the specified parameter,
     *   including the parameter in the sum.
     *   If the parameter value is negative, returns zero.
     * For example,
     *   sumOfPositiveIntegerSequence(3) should return 6;
     *   sumOfPositiveIntegerSequence(-5) should return 0.
     * 
     * @param  limit   the largest value included in the sum (if positive)
     * @return the sum of integers in the range 1...limit inclusive
     *         if the parameter is positive; 0 otherwise
     */
    public int sumOfPositiveIntegerSequence(int limit) {
     int sum = 0;
     if(limit > 0) {
  
        for(int num = 0; num <= limit; num++) {
        sum += num;
        }
        return sum;
     }
     return 0;
     }

    /**
     * Returns a list consisting of the initial item in the parameter
     * followed by every other item in the parameter.
     * For example, if the original list was ["my", "dog", "has", "fleas", "!"]
     * then the list returned would be ["my", "has", "!"].
     * 
     * @param  original   the list from which the returned list is constructed
     * @return a list containing every other element of the original list
     */
    public  ArrayList<String> everyOtherItem(ArrayList<String> original) {
        //create new arraylist with everyother item.
        
        //original.add ("my");
        //original.add ("dog");
        //original.add ("has");
        //original.add ("fleas");
        //original.add ("!");
        
        System.out.println("Every other String:");
        
        for (int i = 0; i <original.size(); i+=2)
        {
            System.out.print(original.get(i) + "");
        }
        return new ArrayList<String>(original);
}

}