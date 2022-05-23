import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
/**
 * Tests for Gym class.
 * @author Jody Paul
 * @version 1.3.1
 */
public class GymTest {
    /** Verifies behavior of accessor getGymName. */
    @Test
    public void testGetGymName() {
        String name = "Gym Name";
        MembershipType[] membershipTypes = {MembershipType.GROUP,
                                            MembershipType.OFF_PEAK,
                                            MembershipType.PEAK};
        Double[] memRates = {50.0, 45.0, 75.0};
        double trainerRate = 25.0;
        Gym gym = new Gym(name, membershipTypes, memRates, trainerRate);
        assertTrue(name.equals(gym.getGymName()));
        Gym gym1 = new Gym("test", MembershipType.values(),
                           new Double[] {20.0, 30.3, 40.4}, 0.0);
        assertEquals("test", gym1.getGymName());
    }

    /** Verifies behavior of accessor getGymRate. */
    @Test
    public void testGetGymRate() {
        String name = "Gym Name";
        MembershipType[] membershipTypes = {MembershipType.GROUP,
                                            MembershipType.OFF_PEAK,
                                            MembershipType.PEAK};
        Double[] memRates = {50.0, 45.0, 75.0};
        double trainerRate = 25.0;
        Gym gym = new Gym(name, membershipTypes, memRates, trainerRate);

        assertTrue(gym.getGymRate(MembershipType.GROUP) == memRates[0]);
        assertTrue(gym.getGymRate(MembershipType.OFF_PEAK) == memRates[1]);
        assertTrue(gym.getGymRate(MembershipType.PEAK) == memRates[2]);

        assertTrue(gym.getGymRate(MembershipType.GROUP) == 50.0);
        assertTrue(gym.getGymRate(MembershipType.OFF_PEAK) == 45.0);
        assertTrue(gym.getGymRate(MembershipType.PEAK) == 75.0);

        Gym gym1 = new Gym("test", MembershipType.values(),
                           new Double[] {0.0d, 0.0d, 0.0d}, 0.0);
        assertEquals(0.0d, gym1.getGymRate(MembershipType.PEAK), 0.0);
        assertEquals(0.0d, gym1.getGymRate(MembershipType.GROUP), 0.0);
        assertEquals(0.0d, gym1.getGymRate(MembershipType.OFF_PEAK), 0.0);

        Gym gym2 = new Gym("test", MembershipType.values(),
                           new Double[] {20.0, 30.3, 40.4}, 0.0);
        assertEquals(20.0d, gym2.getGymRate(MembershipType.GROUP), 0.0);
        assertEquals(30.3d, gym2.getGymRate(MembershipType.OFF_PEAK), 0.0);
        assertEquals(40.4d, gym2.getGymRate(MembershipType.PEAK), 0.0);
    }

    /** Verifies behavior of accessor getMembers. */
    @Test
    public void testGetMembers() {
        String name = "Gym Name";
        MembershipType[] membershipTypes = {MembershipType.GROUP,
                                            MembershipType.OFF_PEAK,
                                            MembershipType.PEAK};
        Double[] memRates = {50.0, 45.0, 75.0};
        double trainerRate = 25.0;
        Gym gym = new Gym(name, membershipTypes, memRates, trainerRate);
        gym.addMember("first1", "last1", MembershipType.GROUP, 1, 2, 3, 4);
        Member member2 = new Member("first2", "last2",
                                    MembershipType.PEAK, 5, 6, 7, 8);
        gym.addMember(member2);

        assertTrue(gym.getMembers().size() == 2);
        assertTrue(gym.getMembers().get(0).getFirstName() == "first1");
        assertTrue(gym.getMembers().get(1).getFirstName() == "first2");
    }

    /** Verifies behavior of accessor getPersonalTrainerRate. */
    @Test
    public void testGetPersonalTrainerRate() {
        String name = "Gym Name";
        MembershipType[] membershipTypes = {MembershipType.GROUP,
                                            MembershipType.OFF_PEAK,
                                            MembershipType.PEAK};
        Double[] memRates = {50.0, 45.0, 75.0};
        double trainerRate = 25.0;
        Gym gym = new Gym(name, membershipTypes, memRates, trainerRate);
        assertTrue(gym.getPersonalTrainerRate() == trainerRate);
        Gym gym1 = new Gym("test", MembershipType.values(),
                           new Double[] {20.0, 30.3, 40.4}, 0.0);
        assertEquals(0.0, gym1.getPersonalTrainerRate(), 0.0);
        Gym gym2 = new Gym("test", MembershipType.values(),
                           new Double[] {20.0, 30.3, 40.4}, 5.0);
        assertEquals(5.0, gym2.getPersonalTrainerRate(), 0.0);
    }

    /** Verifies behavior of mutator setGymName. */
    @Test
    public void testSetGymName() {
        String name = "Gym Name";
        MembershipType[] membershipTypes = {MembershipType.GROUP,
                                            MembershipType.OFF_PEAK,
                                            MembershipType.PEAK};
        Double[] memRates = {50.0, 45.0, 75.0};
        double trainerRate = 25.0;
        Gym gym = new Gym(name, membershipTypes, memRates, trainerRate);
        String newName = "New Gym Name";
        gym.setGymName(newName);
        assertTrue(gym.getGymName() == newName);
        Gym gym1 = new Gym("test", MembershipType.values(),
                           new Double[] {20.0, 30.3, 40.4}, 0.0);
        gym1.setGymName("diff");
        assertEquals("diff", gym1.getGymName());
    }

    /** Verifies behavior of mutator setGymRate. */
    @Test
    public void testSetGymRate() {
        String name = "Gym Name";
        MembershipType[] membershipTypes = {MembershipType.GROUP,
                                            MembershipType.PEAK};
        Double[] memRates = {50.0, 45.0};
        double trainerRate = 25.0;
        Gym gym = new Gym(name, membershipTypes, memRates, trainerRate);
        gym.setGymRate(MembershipType.PEAK, 25.0);

        /* OPTIONAL Visually confirm that an error message is
         * written to the console */
        //gym.setGymRate(MembershipType.OFF_PEAK, 20.0);

        assertTrue(gym.getGymRate(MembershipType.PEAK) == 25.0);
        Gym gym1 = new Gym("test", MembershipType.values(),
                           new Double[] {20.0, 30.3, 40.4}, 0.0);
        gym1.setGymRate(MembershipType.OFF_PEAK, 5.0);
        assertEquals(5.0d, gym1.getGymRate(MembershipType.OFF_PEAK), 0.0);
        Gym gym2 = new Gym("test", MembershipType.values(),
                           new Double[] {20.0, 30.3, 40.4}, 0.0);
        gym2.setGymRate(MembershipType.PEAK, 100.0);
        assertEquals(100.0d, gym2.getGymRate(MembershipType.PEAK), 0.0);
    }

    /** Verifies behavior of mutator setPersonalTrainerRate. */
    @Test
    public void testSetPersonalTrainerRate() {
        String name = "Gym Name";
        MembershipType[] membershipTypes = {MembershipType.GROUP,
                                            MembershipType.OFF_PEAK,
                                            MembershipType.PEAK};
        Double[] memRates = {50.0, 45.0, 75.0};
        double trainerRate = 25.0;
        Gym gym = new Gym(name, membershipTypes, memRates, trainerRate);
        gym.setPersonalTrainerRate(45.0);
        assertTrue(gym.getPersonalTrainerRate() == 45.0);
        Gym gym1 = new Gym("test", MembershipType.values(),
                           new Double[] {20.0, 30.3, 40.4}, 0.0);
        gym1.setPersonalTrainerRate(10.0d);
        assertEquals(10.0d, gym1.getPersonalTrainerRate(), 0.0);
    }

    /** Verifies behavior of toString. */
    @Test
    public void testToString() {
        String name = "Gym Name";
        MembershipType[] membershipTypes = {MembershipType.GROUP,
                                            MembershipType.OFF_PEAK,
                                            MembershipType.PEAK};
        Double[] memRates = {50.0, 45.0, 75.0};
        double trainerRate = 25.0;
        Gym gym = new Gym(name, membershipTypes, memRates, trainerRate);
        assertTrue(gym.toString().equals("Gym Name: " + name));
    }
}
