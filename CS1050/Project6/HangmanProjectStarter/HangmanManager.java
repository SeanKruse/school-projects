import java.util.Set;
import java.util.SortedSet;

/**
 * A HangmanManager keeps track of the state of a game of hangman.
 * Methods provide access to the current set of guesses,
 * number of wrong guesses remaining, and the current pattern
 * to be displayed to the user.
 * The <CODE>record</CODE> method updates state by recording a new guess
 * and updating the other values appropriately.
 * <P>
 * Implementing classes are expected include a constructor as follows.
 * </P><PRE>
 * public ClassName(final List&lt;String&gt; dictionary,
 *                  final int length,
 *                  final int wrongGuessLimit)
 * </PRE>
 * <UL>
 * <LI>The dictionary parameter is a list of candidate words.</LI>
 * <LI>The length parameter is the length of a goal word.</LI>
 * <LI>The wrongGuessLimit parameter is the number of wrong guesses at which
 *         the player loses.</LI>
 * <LI>The constructor throws and IllegalArgumentException if length &lt; 1,
 *     wrongGuessLimit &lt; 1, or there is no word in the dictionary of
 *     the specified length.
 * </LI>
 * </UL>
 *
 * @author Dr. Jody Paul
 * @version 1.4.3
 */
public interface HangmanManager  {
    /**
     * Access the set of candidate words;
     *     if size == 1, contents are the actual goal word.
     * @return the goal word or the candidate goal words
     */
    Set<String> words();

    /**
     * Access the limit on wrong guesses.
     * @return the number of wrong guesses that results in a player loss
     */
    int wrongGuessLimit();

    /**
     * Access the number of wrong guesses that result in a loss
     *     for the player given the current state of the game.
     * @return the number of wrong guesses that would result in a loss
     */
    int guessesLeft();

    /**
     * Access the set of letters already guessed by the player.
     * @return the set of letters guessed by the player
     */
    SortedSet<Character> guesses();

    /**
     * Return the hangman-style display pattern of letters and dashes
     * (with interpolated spaces) appropriate to the current state
     * based on the letters already guessed and the goal.
     * @throws IllegalStateException if there is no goal word
     * @return the hangman-style pattern to be displayed to the user
     */
    String pattern();

    /**
     * Record state changes based on new letter guess.
     * @throws IllegalStateException if no guesses left or no goal word
     * @throws IllegalArgumentException if letter is already guessed
     * @param guess the letter being guessed
     *   [Precondition: guess must be lower-case letter]
     *   [Precondition: guess must not be among letters already guessed]
     * @return the number of occurrences of the guessed letter in the goal
     */
    int record(char guess);
}
