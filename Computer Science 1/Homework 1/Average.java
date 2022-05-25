// This program adds three numbers, calculates the average then
// displays it on the computer screen.
// CS1050 - 005 - Sean Kruse

// Libraries
import java.io.*;
import java.util.*;

// Name of the class is also the 
// name of the file that contains the code.
public class Average {
	
	// variables
	static int a, b, c;
	static double avg;	
	static boolean goodInput = true;
   
	// a function to display on the screen
	public static void sop(String s){
		System.out.println(s);
	}
	
	// a function that asks the user to input 3 numbers
	public static void readUserInput() {

		Scanner keyboard = new Scanner(System.in);
		try {
			sop("Enter three integers: ");
			sop("Input value a: ");
			a = keyboard.nextInt();
			
			sop("Input value b: ");
			b = keyboard.nextInt();
			
			sop("Input value c: ");
			c = keyboard.nextInt();
		} catch (Exception e) {
			sop("Bad input");
			goodInput = false;
		}
	}

	// The main (driver) program. 
	public static void main(String []args) {		
		
		// call the user input function.
	    readUserInput();
		
		//a = 20;
		//b = 40;
		//c = 60;
      
      //Print the three numbers entered by user
      System.out.println( "You entered these three numbers: " + a  + ", " + b + ", " + c );
      
      //Print three integer values multiplied
      System.out.println ( "Three integer values multiplied: " + a * b * c );
		
		// if the three values are ok, calculate average
		if (goodInput) {
			avg = (a + b + c)/3.0;
			sop ("Average of three double values: " + avg);
		} 
		
		// otherwise, can't get the average.
		else sop("No average possible");
	}
}