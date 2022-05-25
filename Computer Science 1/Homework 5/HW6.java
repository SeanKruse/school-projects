/**
 * Colfax Marathon
 *
 * @Sean Kruse
 * @version1 - 03/20/2018
 */
public class HW6 {

public static void main (String[] arguments) {

   String[] names = {
      "Elena", "Thomas", "Hamilton", "Suzie", "Phil", "Matt", "Alex", "Emma", "Carlos", "James", "Jane", "Emily", "Daniel", "Neda", "Aaron", "Kate"
   };
   
   int[] times = {
      341, 273, 278, 329, 445, 402, 388, 275, 243, 334, 412, 393, 299, 343, 317, 265
   };
	
   int index = findShortest(times);  
    
   System.out.println("The fastest person was " + names[index] + " with a time of " + times[index] + " minutes.");
   
	int second = findSecondShortest(times);	
   
	System.out.println("The second fastest person was " + names[second] + " with a time of " + times[second] + " minutes.");
   
  }//end main
  
  //method 1
  public static int findShortest (int[] times){
  
  int index = 0;
  int i = 0;
  
  for (i = 0; i < times.length; i++){
  
   if(times[i] < times[index]) {
   
   index = i;
   
   }//end if-statement
   
  
  }//end for loop
  
  return index;
  
 }// end method 1
  
  //method 2
  public static int findSecondShortest (int[] nums){
   
   int first = findShortest(nums);
   int second = 0;
   int i = 0;
   
   for (i = 0; i < nums.length; i++) {
   
   if(nums[i] < nums[second] && i != first){
   
   second = i;
   
   }//end if-statement
   
   }//end for-loop
  
  return second;
  
  }//end method 2
  
   //System.out.println("The remaining runners in descending order of time: ");
   
 }//end class