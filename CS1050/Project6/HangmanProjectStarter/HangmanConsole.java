import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * Class HangmanConsole includes a static main method that
 * reads a dictionary of words to be used during the game,
 * then plays a game with the user via the console.
 * @author Dr. Jody Paul
 * @version 1.2
 */
public final class HangmanConsole {
    /** Location (filename) of dictionary file. */
    public static final String DICTIONARY_FILE = "dictionary.txt";

    /**
     * Ensure no visible constructor for this utility class.
     */
    private HangmanConsole() { }

    /**
     * Driver to run the hangman game.
     * @param args ignored
     * @throws FileNotFoundException if dictionary file not available
     */
    public static void main(final String[] args) throws FileNotFoundException {
        System.out.println("Welcome to the hangman word-guessing game.");
        System.out.println();

        // Open the dictionary file and read dictionary into a List.
        Scanner input = new Scanner(new File(DICTIONARY_FILE));
        List<String> dictionary = new ArrayList<String>();
        while (input.hasNext()) {
            dictionary.add(input.next().toLowerCase());
        }
        // Set basic parameters.
        Scanner console = new Scanner(System.in);
        System.out.print("What length word do you want to use? ");
        int length = console.nextInt();
        System.out.print("How many wrong answers allowed? ");
        int max = console.nextInt();
        System.out.println();

        // Set up a HangmanManager and start the game.
        List<String> dictionary2 = Collections.unmodifiableList(dictionary);
        HangmanManager hanager = new Hangman(dictionary2, length, max);
        if (hanager.words().isEmpty()) {
            System.out.println("No words of that length in the dictionary.");
        } else {
            playGame(console, hanager);
            showResults(hanager);
        }
    }

    /**
     * Plays one game with the user.
     * @param console the Scanner console for user interaction
     * @param hanager the hangman manager
     */
    public static void playGame(final Scanner console,
                                final HangmanManager hanager) {
        while (hanager.guessesLeft() > 0 && hanager.pattern().contains("-")) {
            System.out.println("Wrong guesses remaining : "
                               + hanager.guessesLeft());
            System.out.println("guessed : " + hanager.guesses());
            System.out.println("current : " + hanager.pattern());
            System.out.print("Your guess? ");
            char ch = console.next().toLowerCase().charAt(0);
            if (hanager.guesses().contains(ch)) {
                System.out.println("You already guessed that");
            } else {
                int count = hanager.record(ch);
                if (count == 0) {
                    System.out.println("Sorry, there are no " + ch + "'s");
                } else if (count == 1) {
                    System.out.println("Yes, there is one " + ch);
                } else {
                    System.out.println("Yes, there are " + count + " " + ch
                                       + "'s");
                }
            }
            System.out.println();
        }
    }

    /**
     * Report the results of the game,
     * including showing the answer if appropriate.
     * @param hanager the hangman play manager
     */
    public static void showResults(final HangmanManager hanager) {
        // Retrieve and display goal (the first word in the list).
        String answer = hanager.words().iterator().next();
        System.out.println("answer = " + answer);
        // Indicate win or lose.
        if (hanager.guessesLeft() > 0) {
            System.out.println("You beat me");
        } else {
            System.out.println("Sorry, you lose");
        }
    }
}
