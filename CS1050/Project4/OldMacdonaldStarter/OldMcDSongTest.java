import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
/**
 * Tests for the OldMcDSong class.
 *
 * @author  Dr. Jody Paul
 * @version 20180920.1.1
 */
public class OldMcDSongTest {
    /** List of animals for testing. */
    private ArrayList<Animal> farmAnimals = new ArrayList<Animal>();

    /** Add animals to the collection of farm animals. */
    public void populateFarm() {
        farmAnimals.add(new Animal("pig", "oink"));
        farmAnimals.add(new Animal("cow", "moo"));
        farmAnimals.add(new Animal("dog", "woof"));
        farmAnimals.add(new Animal("cat", "meow"));
        farmAnimals.add(new Animal("mouse", "squeak"));
    }

    /**
     * Test of the verseIntro(Animal) helper method.
     */
    @Test
    public void verseIntroTest() {
        OldMcDSong testSong = new OldMcDSong();
        assertEquals("Old MacDonald had a farm, E-I-E-I-O\n"
            + "And on that farm he had a horse, E-I-E-I-O\n"
            + "With a neigh neigh here and a neigh neigh there\n"
            + "Here a neigh, there a neigh, everywhere a neigh neigh\n",
            testSong.verseIntro(new Animal("horse", "neigh")));

        populateFarm();
        Animal randomAnimal
            = farmAnimals.get((int) (farmAnimals.size() * Math.random()));
        String randomAnimalName = randomAnimal.getName();
        String randomAnimalSound = randomAnimal.getSound();
        assertEquals("Old MacDonald had a farm, E-I-E-I-O\n"
            + "And on that farm he had a "
            + randomAnimalName + ", E-I-E-I-O\n"
            + "With a "
            + randomAnimalSound + " " + randomAnimalSound
            + " here and a "
            + randomAnimalSound + " " + randomAnimalSound
            + " there\n"
            + "Here a " + randomAnimalSound
            + ", there a " + randomAnimalSound
            + ", everywhere a "
            + randomAnimalSound + " " + randomAnimalSound
            + "\n",
            testSong.verseIntro(randomAnimal));
    }

    /**
     * Test of the couplet(Animal) helper method.
     */
    @Test
    public void coupletTest() {
        OldMcDSong song1 = new OldMcDSong();
        assertEquals("A caw caw here, a caw caw there\n"
            + "Here a caw, there a caw, everywhere a caw caw\n",
            song1.couplet(new Animal("crow", "caw")));
    }

    /**
     * Test of the verse(Animal, ArrayList<Animal>) helper method.
     */
    @Test
    public void verseTest() {
        OldMcDSong testSong = new OldMcDSong();
        farmAnimals = new ArrayList<Animal>();
        farmAnimals.add(new Animal("cat", "meow"));
        farmAnimals.add(new Animal("crow", "caw"));

        assertEquals("Old MacDonald had a farm, E-I-E-I-O\n"
            + "And on that farm he had a horse, E-I-E-I-O\n"
            + "With a neigh neigh here and a neigh neigh there\n"
            + "Here a neigh, there a neigh, everywhere a neigh neigh\n"
            + "A meow meow here, a meow meow there\n"
            + "Here a meow, there a meow, everywhere a meow meow\n"
            + "A caw caw here, a caw caw there\n"
            + "Here a caw, there a caw, everywhere a caw caw\n"
            + "Old MacDonald had a farm, E-I-E-I-O\n",
            testSong.verse(new Animal("horse", "neigh"),
                farmAnimals));

        populateFarm();
        Animal randimal0 = farmAnimals.get((int) (farmAnimals.size() * Math.random()));
        Animal randimal1 = farmAnimals.get((int) (farmAnimals.size() * Math.random()));
        Animal randimal2 = farmAnimals.get((int) (farmAnimals.size() * Math.random()));
        String randimal0Name = randimal0.getName();
        String randimal0Sound = randimal0.getSound();
        farmAnimals = new ArrayList<Animal>();
        farmAnimals.add(randimal1);
        farmAnimals.add(randimal2);
        String randimal1Sound = randimal1.getSound();
        String randimal2Sound = randimal2.getSound();
        assertEquals("Old MacDonald had a farm, E-I-E-I-O\n"
            + "And on that farm he had a " + randimal0Name + ", E-I-E-I-O\n"
            + "With a " + twoSounds(randimal0Sound)
            + " here and a " + twoSounds(randimal0Sound) + " there\n"
            + "Here a " + randimal0Sound + ", there a " + randimal0Sound
            + ", everywhere a " + twoSounds(randimal0Sound) + "\n"
            + "A " + twoSounds(randimal1Sound)
            + " here, a " + twoSounds(randimal1Sound) + " there\n"
            + "Here a " + randimal1Sound + ", there a " + randimal1Sound
            + ", everywhere a " + twoSounds(randimal1Sound) + "\n"
            + "A " + twoSounds(randimal2Sound)
            + " here, a " + twoSounds(randimal2Sound) + " there\n"
            + "Here a " + randimal2Sound + ", there a " + randimal2Sound
            + ", everywhere a " + twoSounds(randimal2Sound) + "\n"
            + "Old MacDonald had a farm, E-I-E-I-O\n",
            testSong.verse(randimal0,
                farmAnimals));
    }

    /**
     *  Utility that concatenates a sound with itself separated by a space.
     *  @param sound the sound to duplicate
     *  @return the duplicated sound
     */
    private String twoSounds(final String sound) {
        return sound + " " + sound;
    }
}
