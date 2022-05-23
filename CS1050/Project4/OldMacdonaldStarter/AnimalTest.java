import static org.junit.Assert.assertEquals;
import org.junit.Test;
/**
 * The test class AnimalTest.
 *
 * @author  Jody Paul
 * @version 20180920.1.1
 */
public class AnimalTest {
    /** Test the constructor and accessors for name and sound. */
    @Test
    public void attributesTest() {
        Animal animal1 = new Animal("cow", "moo");
        assertEquals("cow", animal1.getName());
        assertEquals("moo", animal1.getSound());
        Animal animal2 = new Animal("dog", "woof");
        assertEquals("dog", animal2.getName());
        assertEquals("woof", animal2.getSound());
    }
}
