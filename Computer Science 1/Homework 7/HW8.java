import java.io.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
/**
 * A  HW8 class that takes a user defined named file and outputs the 
 * smallest integer, largest integer, and a count of the number of lines.
 * Methods provide access to read the incoming user-defined file,
 * iterate through the integers in said file, and finally output the required info.
 * Defensive blocks throughout prevent different files from crashing program.
 * @author Sean Kruse v1.3
 */
public class HW8 {
 Scanner userinput = new Scanner(System.in);
 ArrayList < Integer > list = new ArrayList < Integer > ();
 int nlines = 0;
 String inputFile = "";
 String outputFile = "";
 Scanner reader = null;
 BufferedWriter writer = null;
 final boolean DEBUG = true;

 public void getInput() {
  System.out.println("Input file name? The program will automatically add the .txt extension to the name. ");
  inputFile = userinput.next() + ".txt";
  try {
   File input = new File(inputFile);
   reader = new Scanner(input);
  } catch (Exception FileNotFoundException) {
   System.out.println("File was not found, please run the program again.");
   System.exit(0);
  }
  System.out.println("Output file name? The program will automatically add the .out extension to the name. ");
  outputFile = userinput.next() + ".out";
  try {
   writer = new BufferedWriter(new FileWriter(outputFile));
  } catch (Exception e) {
   System.out.println("Oops, something went wrong! Please run the program again.");
   System.exit(0);
  } //catch (FileNotFoundException) {
  //System.out.println("File not Found");
 }//end getInput method
 //method that reads incoming file and adds it to the list ArrayList
 public void readFile() {
  while (reader.hasNext()) {
   try {
    int current = reader.nextInt();
    //if(DEBUG){
    //System.out.println("DEBUG: READING\t"+current);
    //}
    list.add(current);
   } catch (Exception InputMismatchException) {
    System.out.print("Was expecting an Integer. Please try again with a file of integers" + "\n");
    System.exit(0);
   } //catch (Exception e) {
   //System.out.println("Oops, something went wrong!");
   //}
  } //end while
 } //end readfile

 public void createFileOutput() {
  int smallest = 0;
  int largest = 0;
  if (list.size() > 0) {
   smallest = list.get(0);
   largest = list.get(0);

   for (int i = 0; i < list.size(); i++) {
    if (smallest >= list.get(i)) {
     smallest = list.get(i);
    }
    if (largest <= list.get(i)) {
     largest = list.get(i);
    }
   } //end for-loop
  } // end if-statement
  try {
   writer.write("Smallest: " + String.valueOf(smallest) + "\n");
   writer.write("Largest: " + String.valueOf(largest) + "\n");
   writer.write("Count: " + String.valueOf(list.size()));
   writer.close();
  } catch (IOException e) {}
 } //end getFileDetails

 public static void main(String args[]) {
  HW8 temp = new HW8();
  temp.getInput();
  temp.readFile();
  temp.createFileOutput();
  System.out.println("Congratulations. You successfully created an output file with the smallest and largest integers, and a count of how many lines!"); 
 } //end main
} //end class