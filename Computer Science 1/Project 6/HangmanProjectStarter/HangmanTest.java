import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * Scaffolding for and examples of test methods for the Hangman class.
 * These must be modified and extended.
 * As presented, this is not a complete effective set of tests.
 *
 * @author  Dr. Jody Paul
 * @version 1.6.0
 */
public class HangmanTest {
    /** Location (filename) of dictionary file. */
    static final String DICTIONARY_FILE = "dictionary.txt";
    /** Maximum number of expected words in dictionary. */
    static final int NUM_WORDS = 130000;
    /** Local storage of dictionary words. */
    List<String> dictionary;
    /** Example source words. */
    String[] exampleWords = { "ally", "beta", "cool", "deal",
                              "else", "flew", "good", "hope", "ibex" };
    /** Example short and long words. */
    String[] shortAndLongWords = { "a", "i",
                                   "electroencephalograph",
                                   "antidisestablishmentarianism" };
    /** Example dictionary. */
    List<String> littleDictionary;
    /** One word dictionary. */
    List<String> oneWordDictionary;
    /** Short and long word dictionary. */
    List<String> shortAndLongDictionary;

    /**
     * Default constructor for test class HangmanTest
     */
    public HangmanTest() {
    }

    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    @Before
    public void setUp() {
        this.littleDictionary = Arrays.asList(exampleWords);
        this.shortAndLongDictionary = Arrays.asList(shortAndLongWords);
        this.oneWordDictionary = Arrays.asList(new String[] {"avocado"});
        // Open the dictionary file and read dictionary into a List.
        try (Scanner input = new Scanner(new File(DICTIONARY_FILE))) {
            this.dictionary = new ArrayList<String>(NUM_WORDS);
            while (input.hasNext()) {
                this.dictionary.add(input.next().toLowerCase());
            }
        } catch (java.io.FileNotFoundException e) {
            this.dictionary.add("One");
            this.dictionary.add("Two");
            this.dictionary.add("Three");
            this.dictionary.add("Four");
            this.dictionary.add("Five");
            this.dictionary.add("Six");
            this.dictionary.addAll(this.littleDictionary);
        }
    }

    /**
     * Tears down the test fixture.
     * Called after every test case method.
     */
    @After
    public void tearDown() {
    }

    /**
     * Checks behavior of the wrongGuessLimit method.
     * <b>TODO Write the body of this method</b>
     */
    @Test
    public void wrongGuessLimitTest() {
        fail("wrongGuessLimitTest not yet written");
        //assertEquals( , hanager.wrongGuessLimit());
    }

    /**
     * Checks that the dictionary was not modified
     * during Hangman construction.
     */
    @Test
    public void dictionaryInitializationTest() {
        int dictionarySize = this.dictionary.size();
        HangmanManager hm = new Hangman(this.dictionary, 3, 5);
        assertEquals("Mutated dictionary parameter.",
                     dictionarySize,
                     this.dictionary.size());
    }

    /**
     * Checks initialization of number of guesses
     * remaining.
     * Relies on the guessesLeft method.
     */
    @Test
    public void numberOfGuessesInitializationTest() {
        HangmanManager hm = new Hangman(dictionary, 3, 5);
        assertEquals(5, hm.guessesLeft());
        hm = new Hangman(littleDictionary, 4, 9);
        assertEquals(9, hm.guessesLeft());
    }

    /**
     * Checks initial pattern, comprised of all dashes.
     */
    @Test
    public void initialPatternTest() {
        HangmanManager hm;
        hm = new Hangman(this.dictionary, 9, 8);
        assertEquals(17, hm.pattern().length());
        assertEquals("- - - - - - - - -", hm.pattern());
        hm = new Hangman(this.dictionary, 3, 8);
        assertEquals(5, hm.pattern().length());
        assertEquals("- - -", hm.pattern());
        hm = new Hangman(oneWordDictionary, 7, 5);
        assertEquals("- - - - - - -", hm.pattern());
        hm = new Hangman(dictionary, 3, 7);
        assertEquals("- - -", hm.pattern());
        hm = new Hangman(dictionary, 5, 2);
        assertEquals("- - - - -", hm.pattern());
        hm = new Hangman(shortAndLongDictionary, 1, 100);
        assertEquals("-", hm.pattern());
        hm = new Hangman(shortAndLongDictionary, 28, 12);
        assertEquals(55, hm.pattern().length());
        assertEquals(28, hm.pattern().chars().filter(c -> c == '-').count());
        assertEquals(27, hm.pattern().chars().filter(c -> c == ' ').count());
    }

    /**
     * Checks that the constructor throws IllegalArgumentException
     * if no words in the dictionary match the desired length.
     */
    @Test(expected = IllegalArgumentException.class)
    public void noWordsMatchLengthTest() {
        HangmanManager hm = new Hangman(littleDictionary, 5, 9);
    }

    /**
     * Checks that the constructor throws IllegalArgumentException
     * if word length is less than 1.
     */
    @Test
    public void invalidWordLengthTest() {
        try {
            HangmanManager hm = new Hangman(littleDictionary, 0, 8);
            fail("Expected IllegalArgumentException for 0 length word");
        } catch (IllegalArgumentException e) {
        }
        try {
            HangmanManager hm = new Hangman(littleDictionary, -2, 8);
            fail("Expected IllegalArgumentException for negative length word");
        } catch (IllegalArgumentException e) {
        }
    }

    /**
     * Checks that the constructor throws IllegalArgumentException
     * if number of guesses is less than 1.
     */
    @Test
    public void notEnoughGuessesAllowedTest() {
        try {
            HangmanManager hm = new Hangman(littleDictionary, 4, 0);
            fail("Expected IllegalArgumentException for 0 guesses allowed");
        } catch (IllegalArgumentException e) {
        }
        try {
            HangmanManager hm = new Hangman(littleDictionary, 4, -2);
            fail("Expected IllegalArgumentException for negative guesses allowed");
        } catch (IllegalArgumentException e) {
        }
    }

    /**
     * Checks that the record method throws IllegalStateException
     * if no guesses left.
     */
    @Test(expected = IllegalStateException.class)
    public void tooManyGuessesTest() {
        HangmanManager hm = new Hangman(littleDictionary, 4, 1);
        hm.record('z');
        hm.record('a'); // One too many guesses throws exception.
    }

    /**
     * Checks that the record method throws IllegalArgumentException
     * if a correct letter was already guessed.
     */
    @Test(expected = IllegalArgumentException.class)
    public void repeatedGoodGuessTest() {
        HangmanManager hm = new Hangman(oneWordDictionary, 7, 6);
        hm.record('a');
        hm.record('a'); // Already guessed this letter.
    }

    /**
     * Checks that the record method throws IllegalArgumentException
     * if an incorrect letter was already guessed.
     */
    @Test(expected = IllegalArgumentException.class)
    public void repeatedBadGuessTest() {
        HangmanManager hm = new Hangman(littleDictionary, 4, 5);
        hm.record('z');
        hm.record('z'); // Already guessed this letter.
    }

    /**
     * Checks that pattern doesn't change if no guess
     * or after wrong guesses.
     * Relies on the record method.
     */
    @Test
    public void stablePatternTest() {
        HangmanManager hm;
        String basePattern = "- - - - - - -";
        hm = new Hangman(oneWordDictionary, 7, 5);
        assertEquals(basePattern, hm.pattern());
        // Doing nothing should not change pattern.
        assertEquals("Doing nothing changed pattern", basePattern, hm.pattern());
        hm.record('x');
        assertEquals("Wrong guess changed pattern", basePattern, hm.pattern());
        hm.record('b');
        assertEquals("Wrong guess changed pattern", basePattern, hm.pattern());
    }

    /**
     * Checks one path of pattern creations.
     * Relies on the record method.
     */
    @Test
    public void patternTest() {
        HangmanManager hm = new Hangman(this.oneWordDictionary, 7, 4);
        assertEquals(13, hm.pattern().length());
        assertEquals("- - - - - - -", hm.pattern());
        hm.record('e');
        assertEquals("- - - - - - -", hm.pattern());
        hm.record('a');
        assertEquals("a - - - a - -", hm.pattern());
        hm.record('o');
        assertEquals("a - o - a - o", hm.pattern());
        hm.record('b');
        assertEquals("a - o - a - o", hm.pattern());
        hm.record('c');
        assertEquals("a - o c a - o", hm.pattern());
        hm.record('d');
        assertEquals("a - o c a d o", hm.pattern());
        hm.record('v');
        assertEquals("a v o c a d o", hm.pattern());
    }

    /**
     * Checks behavior of the guesses method.
     * Relies on the record method.
     * <b>TODO Write the body of this method</b>
     */
    @Test
    public void guessesTest() {
        fail("guessesTest not yet written");
        //SortedSet<Character> guesses = new TreeSet<>();
        //assertEquals(guesses, hanager.guesses());
    }

    /**
     * Checks integration of record,
     * guesses, and guessesLeft methods.
     */
    @Test
    public void guessExampleTest() {
        HangmanManager hm = new Hangman(littleDictionary, 4, 5);
        assertEquals(0, hm.guesses().size());
        assertEquals(5, hm.guessesLeft());
        hm.record('z');
        assertEquals(1, hm.guesses().size());
        assertTrue(hm.guesses().contains('z'));
        assertEquals(4, hm.guessesLeft());
        hm.record('v');
        assertEquals(2, hm.guesses().size());
        assertTrue(hm.guesses().contains('v'));
        assertTrue(hm.guesses().contains('z'));
        assertEquals(3, hm.guessesLeft());
        hm.record('u');
        assertEquals(3, hm.guesses().size());
        assertTrue(hm.guesses().contains('u'));
        assertTrue(hm.guesses().contains('v'));
        assertTrue(hm.guesses().contains('z'));
        assertEquals(2, hm.guessesLeft());
        hm.record('e');
        assertEquals(4, hm.guesses().size());
        assertTrue(hm.guesses().contains('e'));
        assertTrue(hm.guesses().contains('u'));
        assertTrue(hm.guesses().contains('v'));
        assertTrue(hm.guesses().contains('z'));
        assertTrue(hm.guessesLeft() <= 2);
    }

    /**
     * Checks integration of record,
     * pattern, and guessesLeft methods when
     * used with a single-word dictionary.
     */
    @Test
    public void recordOneWordTest() {
        HangmanManager hm = new Hangman(oneWordDictionary, 7, 5);
        assertEquals(5, hm.guessesLeft());
        assertEquals(13, hm.pattern().length());
        assertEquals("- - - - - - -", hm.pattern());
        assertEquals(2, hm.record('a'));
        assertEquals(5, hm.guessesLeft());
        assertEquals(0, hm.record('b'));
        assertEquals(4, hm.guessesLeft());
        assertEquals(1, hm.record('c'));
        assertEquals(4, hm.guessesLeft());
        assertEquals(1, hm.record('d'));
        assertEquals(4, hm.guessesLeft());
        assertEquals(0, hm.record('3'));
        assertEquals(3, hm.guessesLeft());
        assertEquals(0, hm.record('z'));
        assertEquals(2, hm.guessesLeft());
        assertEquals(2, hm.record('o'));
        assertEquals(2, hm.guessesLeft());
        assertEquals(0, hm.record('y'));
        assertEquals(1, hm.guessesLeft());
        assertEquals(1, hm.record('v'));
        assertEquals(1, hm.guessesLeft());
        assertEquals("a v o c a d o", hm.pattern());
        assertEquals(0, hm.record('x'));
        assertEquals(0, hm.guessesLeft());
    }
}
