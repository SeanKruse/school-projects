import java.util.Scanner;
/**
 * Implement the method in this class according to the specifications
 * provided in each of the methods' header comments.
 * 
 * @author Sean Kruse
 * @version CS1050 
 */
public class Swapper {
    /**
     * Swaps the values stored at two locations in an array.
     * 
     * For example,
     *   if array foo[] initially contained [0, 10, 22, 30, 44],
     *   after the method invocation swap(foo, 1, 3)
     *   foo would contain [0, 30, 22, 10, 44].
     * 
     * @param  array   the array whose values are to be swapped
     * @param  index1  the index of the first value to be swapped
     * @param  index2  the index of the second value to be swapped
     *     if the parameter is positive; 0 otherwise
     */
    public static void swap(int[] array, int index1, int index2) {
         
         int[] numbers = {0,10,22,30,44};
         int start = 1;
         int finish = 2;
         int temp = numbers[start];
         array[0] = array[1];
         array[1] = temp;
         
         for(int pos=0; pos<numbers.length; pos++)
         System.out.print(numbers[pos] + " ");
         System.out.println();
    }
}