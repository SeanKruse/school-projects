import java.util.*;

/**
 * A Hangman class that keeps track of the state of a game of hangman.
 * Methods provide access to the current set of guesses,
 * number of wrong guesses remaining, and the current pattern
 * to be displayed to the user.
 * 
 * @author Sean Kruse
 */
public class Hangman implements HangmanManager {
    
    /** The length of the word chosen. */
    private int length;
    
    /** The maximum number of guesses allowed. */
    private int maxGuesses;
    
    /** The number of wrong guesses.*/
    private int wrongGuesses;
    
    /** The number of user guesses.*/
    private int guess;
    
    /** Set of sorted guesses. */
    private SortedSet<Character> guesses;
    
    /** The words used in the instance for the game. */
    private String goalWord;
    
    /** The list containing all the possible words. */
    private List<String> dictionary;
    
    /** Set of the word to guess. */
    private TreeSet<String> gWord;
    
    public Hangman(final List<String> dictionary,
    final int length,
    final int maxGuesses) throws IllegalArgumentException {
        
        if (length < 1) {
            throw new IllegalArgumentException("The word must contain more than one letter."); 
        }

        if (maxGuesses < 1) {
            throw new IllegalArgumentException("The limit of guesses must be at least 1.");
        }
        
        this.maxGuesses = maxGuesses;
        this.dictionary = dictionary; 
        this.length = length; 
        this.gWord = new TreeSet<>(); 
        guesses = new TreeSet<>(); 
        
        for (String s : dictionary) {
            if (s.length() == length) {
                gWord.add(s);
            }
        }

        if (gWord.size() == 1) {
            goalWord = gWord.first();
        }

        if (words().isEmpty()) {
            throw new IllegalArgumentException("I don't know any words that are that size.");
        }
        
        wrongGuesses = 0;
        
    }

    private int getRand(int limit) {
        
        Random rand = new Random();
        return rand.nextInt(limit);
        
    }

    /**
     * Access the set of candidate words;
     *     if size == 1, contents are the actual goal word.
     * @return the goal word or the candidate goal words
     */
    public Set<String> words() {
       
        ArrayList<String> chosenWord = new ArrayList<>();
        List<String> candidateWords = new ArrayList<>();
        String randString = new String();
        
        for (int i = 0; i < dictionary.size(); i++) {
            String word = dictionary.get(i);
            candidateWords.add(word);
        }

        for (int k = 0; k < candidateWords.size(); k++) {
            randString = candidateWords.get(k);
            if (randString.length() == length) {
                chosenWord.add(randString);
            }
        }

        if (goalWord == null) {
            goalWord = chosenWord.get(getRand(chosenWord.size()));
            gWord = new TreeSet<>();
            gWord.add(goalWord);
        }
        
        return gWord;
        
    }

    /**
     * Access the limit on wrong guesses.
     * @return the number of wrong guesses that results in a player loss
     */
    public int wrongGuessLimit() {
        return maxGuesses;
    }

    /**
     * Access the number of wrong guesses that result in a loss
     *     for the player given the current state of the game.
     * @return the number of wrong guesses that would result in a loss
     */
    public int guessesLeft() {
        return maxGuesses - wrongGuesses;
    }

    /**
     * Access the set of letters already guessed by the player.
     * @return the set of letters guessed by the player
     */
    public SortedSet<Character> guesses() {
        return guesses;
    }

    /**
     * Return the hangman-style display pattern of letters and dashes
     * (with interpolated spaces) appropriate to the current state
     * based on the letters already guessed and the goal.
     * @throws IllegalStateException if there is no goal word
     * @return the hangman-style pattern to be displayed to the user
     */
    public String pattern() throws IllegalStateException {
        
        if (words().isEmpty()) {
            throw new IllegalStateException("There is no goal word."); 
        }
        
        int i = 0;
        String pattern = "";
        char [] patternWord = goalWord.toCharArray();
        
        for (char x : patternWord) {
            if (guesses.contains(x)) {
                pattern = pattern + x;
            }
            else {
                pattern = pattern + "-";
            }

            if (i < length) {
                pattern = pattern + " ";
            }
        }
        
        pattern = pattern.trim();
        return pattern;  
        
    }

    /**
     * Record state changes based on new letter guess.
     * @throws IllegalStateException if no guesses left or no goal word
     * @throws IllegalArgumentException if letter is already guessed
     * @param guess the letter being guessed
     *   [Precondition: guess must be lower-case letter]
     *   [Precondition: guess must not be among letters already guessed]
     * @return the number of occurrences of the guessed letter in the goal
     */
    public int record(char guess) throws IllegalStateException {
        
        if(maxGuesses == 0) {
            throw new IllegalStateException("You are out of guesses.");
        }

        if(words().isEmpty()) {
            throw new IllegalStateException("You must have at least one guess");
        }

        if (guesses.contains(guess)) {
            throw new IllegalArgumentException("You have already guessed this letter");
        }
        
        guesses.add(guess);
        int count = 0;
        char [] wordGuess = goalWord.toCharArray();
        
        for(int i = 0; i < length; i++) {
            if(wordGuess[i] == guess) {
                count++;
            }
        }

        if(count == 0) {
            maxGuesses--;
        }
        return count;
        
    }
}
