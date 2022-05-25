import java.util.*;
/**
 * Write a description of class PhraseOMatic here.
 *
 * @author Sean Kruse
 * @version1
 */


public class PhraseOMatic {
    public static void main (String[] args) {
    
     //Create Word Lists
     String[] wordListOne = new String[]{"24/7 ", "multi-Tier ", "50,000 foot ", "B-to-B ", "win-win ", "back-end ", "web-based ", "perverted ", "smart ", "six-sigma ", "critical path ", "dynamic ", "strong "};
     String[] wordListTwo = new String[]{"shared ", "branded ", "magic ", "outside-the-box ", "conventional ", "accurate ", "and happy "};
     String[] wordListThree = new String[] {"shoe.", "hoodie.", "portal-gun.", "treasure.", "car.", "camel.", "bottle.", "mom.", "baby.", "poop.", "artificial intelligence."};

    //Lenght of String Lists 
     int oneLength = wordListOne.length;
     int twoLength = wordListTwo.length;
     int threeLength = wordListThree.length;
     
         //Generate three random numbers
     int rand1 = (int) (Math.random() * oneLength);
     int rand2 = (int) (Math.random() * twoLength);
     int rand3 = (int) (Math.random() * threeLength);
     
     String phrase = wordListOne [rand1] + "" + wordListTwo [rand2] + "" + wordListThree [rand3];
     
     System.out.println("What we need is a " + phrase);
   
     }
    }
