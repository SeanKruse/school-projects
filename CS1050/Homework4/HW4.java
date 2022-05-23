import java.util.Scanner;

//Sean Kruse CS1050-005 / @v1 - 2/20/2019

public class HW4 {

//Main Method
public static void main (String [] args){

String employeeName;
Double basePay;

//Accept Inputs
Scanner input = new Scanner ( System.in );
   
System.out.println("Employee Name:");
employeeName = input.nextLine();

System.out.println("Base Pay:");
Double basePay = input.nextDouble;

//Check for negative number
while(basePay < 0.00){
System.out.print("Please enter a number greater than 0.00: ");
grossPay = input.nextDouble();
}

System.out.println("Hours Worked");
Integer hours = input.nextInteger;

}

 public double calculatePay( int hours, double rate )
      {
          if ( hours > 40 )
          { 
              int overHours = hours - 40;
              pay = ( 40 * rate ) + ( overHours * rate );
          }
          else pay = hours * rate;

          return pay;
          
}
}      
