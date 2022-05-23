/**
 * An animal for the Old MacDonald song.
 * @author Dr. Jody Paul
 * @author Sean Kruse
 * @version 20180920.0
 */
public class Animal {
    private String animalName;
    private String animalSound;
    // Insert fields here.

    /**
     * Constructs an Animal with specified name and sound.
     * @param theName the name of the animal
     * @param theSound the sound the animal makes
     */
    public Animal(final String theName, final String theSound) {
         this.animalName = theName;
         this.animalSound = theSound;
    }

    /**
     * Retrieve the name of this animal.
     * @return name the name of this animal
     */
    public String getName() {
        //public String intro
        return this.animalName;
    }

    public String getIntro() {
        
        String intro =  "And on that farm he had a " + animalName + ", E-I-E-I-O\n" + this.getIntro2();
            
            System.out.println(intro);
            
            return intro;
    }
    
    public String getIntro2() {
       
        String out = "With a " + animalSound + " " + animalSound + " here, a " + animalSound + " " + animalSound + " there\n"
        + "Here a " + animalSound + ", there a " + animalSound + ", everywhere a " + animalSound + " " + animalSound + "\n";
        
        System.out.println(out);
        
        return out;
    }
    /**
     * Retrieve the sound of this animal.
     * @return sound the sound of this animal
     */
    public String getSound() {
        return this.animalSound;
    }
}
