import static org.junit.Assert.assertEquals;
import org.junit.Test;
/**
 * Tests for enumeration Quarter.
 *
 * @author  Dr. Jody Paul
 * @version 1.1.1
 */
public class QuarterTest {
    /** Verifies proper ordering of Quarter enum. */
    @Test
    public void basicQuarterEnumTest() {
        Quarter[] quarterValues = Quarter.values();
        assertEquals(4, quarterValues.length);
        assertEquals(Quarter.Q1, quarterValues[0]);
        assertEquals(Quarter.Q2, quarterValues[1]);
        assertEquals(Quarter.Q3, quarterValues[2]);
        assertEquals(Quarter.Q4, quarterValues[3]);
    }
}
