import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
/**
 * Tests for the Member class.
 *
 * @author  Jody Paul
 * @version 1.2.1
 */
public class MemberTest {
    /** Test data member reference. */
    private Member member;

    /** Performs setup before each test. */
    @Before
    public void setup() {
        member = new Member("first", "last",
                            MembershipType.PEAK,
                            20, 15, 35, 45);
    }

    /** Verifies getFirstName accessor. */
    @Test
    public void testGetFirstName() {
        assertTrue(member.getFirstName() == "first");
    }

    /** Verifies getLastName accessor. */
    @Test
    public void testGetLastName() {
        assertTrue(member.getLastName() == "last");
    }

    /** Verifies getMemberType accessor. */
    @Test
    public void testGetMemberType() {
        assertTrue(member.getMemberType() == MembershipType.PEAK);
    }

    /** Verifies getPersonalTrainerHours accessor. */
    @Test
    public void testGetPersonalTrainerHours() {
        assertTrue(member.getPersonalTrainerHours().length == 4);
        assertTrue(member.getPersonalTrainerHours()[0] == 20);
    }

    /** Verifies setFirstName mutator. */
    @Test
    public void testSetFirstName() {
        String newFirst = "newFirst";
        member.setFirstName(newFirst);
        assertEquals(newFirst, member.getFirstName());
    }

    /** Verifies setLastName mutator. */
    @Test
    public void testSetLastName() {
        String newLast = "newLast";
        member.setLastName(newLast);
        assertEquals(newLast, member.getLastName());
    }

    /** Verifies setMembershipType mutator. */
    @Test
    public void testSetMembershipType() {
        member.setMembershipType(MembershipType.GROUP);
        assertTrue(member.getMemberType() == MembershipType.GROUP);
    }

    /** Verifies setPersonalTrainerHours mutator. */
    @Test
    public void testSetPersonalTrainerHours() {
        member.setPersonalTrainerHours(Quarter.Q3, 88);
        assertTrue(member.getPersonalTrainerHours()[2] == 88);
    }

    /** Verifies behavior of toString. */
    @Test
    public void testToString() {
        assertTrue(member.toString().equals("Name: first last"));
    }

    /** Checks appropriate treatment of long names. */
    @Test
    public void testLongNames() {
        Member longNameMember = new Member("TwentyCharactersLong",
                                           "TwentyFourCharactersLong",
                                           MembershipType.PEAK,
                                           20, 15, 35, 45);
        assertEquals("TwentyCharacters", longNameMember.getFirstName());
        assertEquals("TwentyFourCharac", longNameMember.getLastName());
        assertEquals("Name: TwentyCharacters TwentyFourCharac",
                     longNameMember.toString());
        longNameMember.setFirstName("SomewhatLongFirstName");
        assertEquals("SomewhatLongFirs", longNameMember.getFirstName());
        longNameMember.setLastName("SomewhatLongerLastName");
        assertEquals("SomewhatLongerLa", longNameMember.getLastName());
    }
}
