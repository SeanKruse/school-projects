import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.IOException;
import java.util.Arrays;

public class HW7 {
 String name;
 int labCount;
 int studentCount;
 LabClass[] labs;
 Student[] students;
 boolean[][] registration;

 //constructor
 public HW7() {
  this.name = "";
  this.labCount = 0;
  this.studentCount = 0;
  this.labs = new LabClass[labCount];
  this.students = new Student[studentCount];
  this.registration = new boolean[labCount][studentCount];
 }

 //overloaded constructor
 public HW7(int labCount, int studentCount) {
  this.labCount = labCount;
  this.studentCount = studentCount;
  this.labs = new LabClass[labCount];
  this.students = new Student[studentCount];
  this.registration = new boolean[labCount][studentCount];
 }
 
 //main  
 public static void main(String[] args) {
  Scanner kb = new Scanner(System.in);

  System.out.println("Welcome to Student/Class Registration for Labs: ");
  System.out.println("------------------------------------------------");
  System.out.println("Please enter how many labs there are: ");
  int labs = kb.nextInt();
  System.out.println("Now, enter how many students there are: ");
  int students = kb.nextInt();
  HW7 temp = new HW7(labs, students);
  temp.createStudentArr(temp.studentCount, kb);
  temp.createLabArr(temp.labCount, kb);
  temp.loadRegister();
  //temp.tryAgain(kb);

  System.exit(0);

 } //end main
 //creating an array of students
 public void createStudentArr(int capacity, Scanner input) {
  input = new Scanner(System.in);
  boolean goodinput = true;
  int nstudents = this.students.length;
  do {
   for (int j = 0; j < nstudents; j++) {
    try {
     int numstud = j + 1;
     System.out.println("Enter Student " + numstud + "'s name");
     String name = input.nextLine();
     //input.nextLine();
     System.out.println("Enter Student " + numstud + "'s id");
     int id = input.nextInt();
     System.out.println("Enter Student " + numstud + "'s credits");
     int credits = input.nextInt();
     input.nextLine();
     Student temp = new Student(name, id);
     temp.addCredits(credits);
     this.students[j] = temp;
     goodinput = true;
    } catch (InputMismatchException e) {
     input.nextLine();
     j--;
     System.err.println("I'm sorry, but that is not a valid input, please try again.");
     goodinput = false;
    }
   }
  } while (goodinput == false);

 } //end createStudent  
 //create an array of lab
 public void createLabArr(int capacity, Scanner input) {
  input = new Scanner(System.in);
  boolean goodinput = true;
  do {
   System.out.println("Now it's time to create the lab classes.");
   System.out.println("-----------------------------------------");
   for (int j = 0; j < this.labs.length; j++) {
    int labnum = j + 1;
    String room = Integer.toString(labnum);
    try {
     System.out.println("Please enter the details for Lab " + labnum + ": ");
     System.out.println("-----------------------------------------");
     System.out.println("Enter the Lab ID: ");
     int labID = input.nextInt();
     input.nextLine();
     System.out.println("Enter Instructor's Name: ");
     String instructor = input.nextLine();
     System.out.println("Enter the Date and Time of the Lab: ");
     String dateandtime = input.nextLine();

     this.labs[j] = new LabClass(capacity);
     this.labs[j].setLabID(labID);
     this.labs[j].setInstructor(instructor);
     this.labs[j].setTime(dateandtime);
     this.labs[j].setRoom(room);

    } catch (InputMismatchException e) {
     input.nextLine();
     j--;
     System.err.println("I'm sorry, but that is not a valid input, please try again.");
     goodinput = false;
    }
    goodinput = true;
   } //end for loop 
  } while (goodinput == false);
 } //end createLabArr    

 public void loadRegister() {
  Scanner input = new Scanner(System.in);
  registration = new boolean[labs.length][students.length];
  System.out.println("Now that the details are loaded, it's time to assign the students to the labs: ");
  System.out.println("-------------------------------------------------------------------------------");
  for (int j = 0; j < labs.length; j++) {
   int labnum = j + 1;
   System.out.println("Registration for Lab " + labnum + ":");
   System.out.println("------------------------------------");
   for (int k = 0; k < students.length; k++) {
    String name = students[k].getName();
    System.out.println("Register " + name + " for Lab " + labnum + "? Please enter 'Yes' or 'No': ");
    String word = input.nextLine();
    if (word.equalsIgnoreCase("y") || word.equalsIgnoreCase("yes")) {
     registration[j][k] = true;

    } else if (word.equalsIgnoreCase("n") || word.equalsIgnoreCase("no")) {
     registration[j][k] = false;
    } //end if-else if   
   } //end student loop
  } //end lab loop 

  System.out.println("Students that are registered to the lab are displayed as true. Students not registered are shown as false.");
  for (int j = 0; j < labs.length; j++) {
   int labnum = j + 1;
   System.out.println("Lab " + labnum + ":");
   for (int k = 0; k < students.length; k++) {
    String name = students[k].getName();
    System.out.println(name + ": " + registration[j][k]);
   } //end loop
   System.out.println("Student Details: ");
   //System.out.println(name + ": " + id + ", " + credits);
  } //end lab loop
 } //end loadRegister        

 public static boolean tryAgain(Scanner input) {

  do {
   System.out.println("Would you like to register Students to Lab Classes, again? Press 'Y' to start over or 'N' to exit program.");
   String word = input.nextLine();
   if (word.equalsIgnoreCase("yes") || word.equalsIgnoreCase("y")) {
    return true;
   } //end if statement 
   if (word.equalsIgnoreCase("no") || word.equalsIgnoreCase("n")) {
    return false;
   } //end if statement
  } while (true);
 } //end tryAgain
} //end HW7 class