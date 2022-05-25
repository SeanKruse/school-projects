import java.util.Scanner;
import java.io.IOException;
/**
 * FizzBuzz Array
 *
 * @Sean Kruse
 * @version1 - 03/06/2018
 */
public class HW5 {

   public static void main (String [] args) {
   Scanner input = new Scanner(System.in);
   
   do{
   
      fizzBuzz(readuserInput(input));
      } while (tryAgain(input));
  
   
   }
   
   public static int[] readuserInput(Scanner input){
   
   int [] myarray = new int [2];
   
   int attempts = 3;
   
   while (attempts > 0) {
   
   
   try {
   
   
   System.out.println("Please provide a starting integer: ");
   
   myarray[0] = Integer.parseInt(input.nextLine());
   
   System.out.println("Please provide a second integer: ");
   
   myarray[1] = Integer.parseInt(input.nextLine());
   
   break;
   
   }catch (Exception e) {
   
   attempts--;
   
   if (attempts == 0){
   
   System.out.println("You have used all attempts, the program will choose between 0 - 100");
   
   myarray[0] = 0;
   myarray[1] = 100;
   
   }//end of if statement
   
   else { 
   
   System.out.println("You have " + attempts + " attempts remaining.");
   System.out.println("Please try again.");
   
   }//end of else
   
  //System.out.println("Please try again. ");
   
   }//end of catch
   
   }//end of while loop
   
   return myarray; 
   
   }// end of readuserInput 
  
   public static void fizzBuzz(int[] myarray){
   
   System.out.println("FizzBuzz starting number: "+ myarray[0] + "\nFizzBuzz ending number: " + myarray[1]);
   
   for (int i = myarray[0]; i <= myarray[1]; i++) {
 
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.println("FizzBuzz");
            } 
            else if(i % 3 == 0){
                System.out.println("Fizz"); 
            }
            else if(i % 5 == 0){
                System.out.println("Buzz");
            }
            else{System.out.println(i + ", ");
            }
                        
   }//end for loop  
       
   }//fizzBuzz method
   
   public static boolean tryAgain(Scanner input){
   
   do { 
   
   System.out.println("Would you like to try again? Press 'Y' to start over or 'N' to end program.");
    
   String word = input.nextLine(); 
   
   if (word.equalsIgnoreCase("yes") || word.equalsIgnoreCase("y")) { 
   
   return true; 
   
   }//end if statement 
   
   if (word.equalsIgnoreCase("no") || word.equalsIgnoreCase("n")) { 
   
   return false; 
   
   }//end if statement 
   
   } while (true); 
   
   }//end tryAgain

   }//end class
   
