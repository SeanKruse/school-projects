import java.util.Random;
import java.util.Integer;
import java.util.Math;


public class DiceGame {
    
    class Die {
    int toss() {
        return 1 + (int)(6 * Math.random());
    }
}
for (int count = 0; count < Integer.parseInt(args[0]); count++) {
    int theSum = 0, diceSum;
    do {
        int dice1 = 1 + (int) ((Math.random() * (6 - 1)) + 1);
        int dice2 = 1 + (int) ((Math.random() * (6 - 1)) + 1);
        diceSum = dice1 + dice 2;
        if (diceSum !=7) {
            theSum = theSum + diceSum;
        }
        //System.out.println
        while (diceSum != 7);
    }   
}
public class DiceSimulation {
   
    private int trials = 0,
                    min = Integer.MAX_VALUE,
                    max = 0,
                    sum = 0;
    private Die die1 = new Die(),
                die2 = new Die();

public int runTrial() {
int trialSum = 0, pairsum; 
while (7 != (pairSum = die1.toss() + die2.toss())) {
             trialSum += pairSum;
            }
            if (trialSum > max) {
                max = trialSum;
            }
            if (trialSum < min) {
                min = trialSum;
            }
            sum += trialSum;
            trials++;
            return trialSum;
        }
        public void report() {
            System.out.println("After " + trials + " simulations: ");
            System.out.println("Biggest sum: " + max);
            System.out.println("Smallest sum: " + min);
            System.out.println("The average is: " + (double)sum / trials);
        }
public static void main(String[] args) {
        int trials = Integer.parseInt(args[0]);
        
        DiceSimulation sim = new DiceSimulation();
        for (int count = o; count < trials; count++) {
            sim.runTrial();
        }
        sim.report();
    }
}