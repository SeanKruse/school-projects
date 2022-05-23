// CS1050 - 005 - Sean Kruse - HW3
import java.util.Scanner;

public class HW3 {

public static void main(String[] args){

//Accept Inputs
Scanner input = new Scanner ( System.in );

Double grossPay;
Double savingsPer;
Double iraPer;
Double savingsTotal;
Double iraTotal;

//My Name:
System.out.print("My Name: ");
String myName = input.nextLine();

//Enter the gross pay:
System.out.print("Enter Gross Pay: ");
grossPay = input.nextDouble();

//Check for negative number
while(grossPay < 0.00){
System.out.print("Please enter a number greater than 0.00: ");
grossPay = input.nextDouble();
}

//Enter the savings percentage %:
System.out.print("Enter the savings % number: ");
savingsPer = input.nextDouble();

//Enter the IRA percentage %:
System.out.print("Enter the IRA percentage % number: ");
iraPer = input.nextDouble();

//Percentage of Savings and IRA
iraTotal = ((iraPer / 100) * grossPay);
savingsTotal = ((savingsPer / 100 * grossPay));

//Display as output
//System.out.println("My Name: " + myName);

System.out.println("");

//Gross pay:
System.out.println("Gross Pay: " + "$" + grossPay);

//Savings %:
System.out.println("Savings %: " + savingsPer + "%");

//Savings amount:
System.out.println("Savings Amount: " + "$" + savingsTotal);

//IRA %:
System.out.println("IRA %: " + iraPer + "%");

//IRA investment amount:
System.out.println("IRA investment amount: " + "$" + iraTotal);

//Total of savings and IRA amounts:
Double totalSavings = savingsTotal + iraTotal;
System.out.println("Total of savings and IRA amounts:" + "$" + totalSavings);

 }
}