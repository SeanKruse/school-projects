import static org.junit.Assert.assertEquals;
import org.junit.Test;
/**
 * Tests for enumeration MembershipType.
 *
 * @author  Dr. Jody Paul
 * @version 1.1.1
 */
public class MembershipTypeTest {
    /** Verifies proper ordering of MembershipType enum. */
    @Test
    public void basicMembershipTypeEnumTest() {
        MembershipType[] membershipTypeValues = MembershipType.values();
        assertEquals(3, membershipTypeValues.length);
        assertEquals(MembershipType.GROUP, membershipTypeValues[0]);
        assertEquals(MembershipType.OFF_PEAK, membershipTypeValues[1]);
        assertEquals(MembershipType.PEAK, membershipTypeValues[2]);
    }
}
