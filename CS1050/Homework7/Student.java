
/**
 * The Student class represents a student in a student administration system.
 * It holds the student details relevant in our context.
 * 
 * @author Michael Kölling and David Barnes
 * @version 2016.02.29
 */
public class Student
{
	// attributes
    public String name;
    public int id;
    public int credits;

    // Constructor - Create a new student with a given name and ID number.
    public Student(String fullName, int studentID)
    {
        name = fullName;
        id = studentID;
        credits = 0;
    }

    // A "getter" - Return the full name of this student.
    public String getName()
    { return name; }

    // A setter - change the name of this student.
    public void changeName(String replacementName)
    { name = replacementName; }

    //Return the student ID of this student.

    public int getStudentID()
    { return id; }

    //Add some credit points to the student's accumulated credits.
    public void addCredits(int additionalPoints)
    { credits += additionalPoints; }

    //Return the number of credit points this student has accumulated.

    public int getCredits()

    { return credits; }

    /**
     * Return the login name of this student. The login name is a combination
     * of the first four characters of the student's name and the first three
     * characters of the student's ID number.

      *** No longer used ***
    public String getLoginName()
    { return name.substring(0,4) + id.substring(0,3);  }
    */
    // Display the student's name and ID number.

    public void print()
    { System.out.println(name + ", student ID: " + id + ", credits: " + credits); }

/* ************ New routines ************* */
	public void changeID(int s) {
		id = s;
	}


} // end class Student

