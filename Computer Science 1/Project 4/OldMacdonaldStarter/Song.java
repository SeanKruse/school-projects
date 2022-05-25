import java.util.ArrayList;
/**
 * Old MacDonald Had a Farm song lyric generator.
 * Each verse introduces an animal by its name and the sound it makes.
 * The introduction is followed by the sounds of previously introduced
 * animals, in reverse order.
 *
 * @author Dr. Jody Paul
 * @author Sean Kruse
 * @version 20180920.1.0
 */
public class Song {
   //List of known animals.
    private ArrayList<Animal> farmAnimals;

    /**
     * Constructs a song with a default set of animals.
     */
    public Song() {
        //Array list with a default set of animals
        //farmAnimals = new ArrayList<Animal>();
        /*farmAnimals.add(new Animal("Cow", "Moo"));
        farmAnimals.add(new Animal("Horse", "Neigh"));
        farmAnimals.add(new Animal("Duck", "Quack"));
        farmAnimals.add(new Animal("Dog", "Neigh"));
        farmAnimals.add(new Animal("Cat", "Meow"));
        farmAnimals.add(new Animal("Sheep", "Baaaa"));
        farmAnimals.add(new Animal("Pig", "Oink"));
        farmAnimals.add(new Animal("Crow", "Caw"));*/
    }

    /**
     * Constructs a lyric generator with specified animals.
     * @param theAnimals the animals to sing about
     */
    public Song(final ArrayList<Animal> theAnimals) {
        this.farmAnimals = theAnimals;  
    }

    /**
     * Creates lyrics for all verses of this song.
     * A single blank line appears before each verse.
     * @return the ready-to-display lyrics
     */
    public String lyrics() {
        String theWords = "";
        ArrayList<Animal> alreadySeenAnimals = new ArrayList<Animal>();
        
        //Loop through every animal
        for(Animal an: farmAnimals){
        
            //create verse for that animal
            theWords += verse(an,alreadySeenAnimals);
            alreadySeenAnimals.add(0, an);
        }   
        
        /* functionally identical to the above loop.
         * for(int i = 0 ; i< farmAnimals.size(); i++){
         *     theWords+= verse(farmAnimals.get(i),alreadySeenAnimals);
         *     alreadySeenAnimals.add(0,farmAnimals.get(i));
         * }
         */
        return theWords;
    }

    /**
     * Creates a verse that introduces a specifed animal
     * and repeats the sounds of previously seen animals.
     * @param introAnimal the animal to introduce
     * @param oldAnimals the previously seen animals
     * @return the ready-to-display lyrics
     */
    public String verse(final Animal introAnimal,
                        final ArrayList<Animal> oldAnimals) {
        
        String theVerse = verseIntro(introAnimal);
        
        //verse += introAnimal.getIntro();
        
        for(Animal a : oldAnimals){
            theVerse += couplet(a);
        }
        
        return theVerse + "Old MacDonald had a farm, E-I-E-I-O\n";
    }

    /**
     * Creates the first four lines of a new verse.
     * Each line is terminated with a newline character.
     * The structure of these lines is as follows:
     * <PRE>
     * Old MacDonald had a farm, E-I-E-I-O
     * And on that farm he had a <I>name</I>, E-I-E-I-O
     * With a <I>sound sound</I> here and a <I>sound sound</I> there
     * Here a <I>sound</I>, there a <I>sound</I>, everywhere a <I>sound sound</I>
     * </PRE>
     * @param theAnimal the animal introduced by this verse
     * @return the ready-to-display lines of lyric
     */
    public String verseIntro(final Animal theAnimal) {
        String sound = theAnimal.getSound();
        
        String name = theAnimal.getName();
        
        String preamble = "Old MacDonald had a farm, E-I-E-I-O\n" +
        "And on that farm he had a "+ name + ", E-I-E-I-O\n"+
        "With a " + sound + " " + sound + " here and a " + sound + " " + sound + " there\n"+
        "Here a " + sound + ", there a " + sound + ", everywhere a " + sound + " " + sound+ "\n";
       
        return preamble;
    }

    /**
     * Creates the two-line couplet for an already seen animal.
     * Each line is terminated with a newline character.
     * The structure of these lines is as follows:
     * <PRE>
     * A <I>sound sound</I> here, a <I>sound sound</I> there
     * Here a <I>sound</I>, there a <I>sound</I>, everywhere a <I>sound sound</I>
     * </PRE>
     * @param anim the animal
     * @return the two-line couplet
     */
    public String couplet(final Animal anim) {
        
        String sound = anim.getSound();
        
        String name = anim.getName();
        
        String couplet = "A "+ sound +" "+ sound+" here, a "+ sound +" "+ sound+" there\n"+
        "Here a "+ sound +", there a "+ sound +", everywhere a "+ sound +" "+ sound+"\n";
        
        return couplet;
    }
}
