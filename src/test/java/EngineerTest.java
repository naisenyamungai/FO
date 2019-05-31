//import models.Engineer;
//import org.junit.*;
//import static org.junit.Assert.*;
//
//public class EngineerTest {
//    @Rule
//    public DatabaseRule database = new DatabaseRule();
//
//    @Before
//    public void setUp() throws Exception {
//    }
//
//    @After
//    public void tearDown() throws Exception {
//    }
//
//    @Test
//    public void engineer_instantiatesCorrectly_true() {
//        Engineer testEngineer = new Engineer("Henry", "EK6487");
//        assertEquals(true, testEngineer instanceof Engineer);
//    }
//
//    @Test
//    public void getName_engineerInstantiatesWithName_Henry() {
//        Engineer testEngineer = new Engineer("Henry", "EK6487");
//        assertEquals("Henry", testEngineer.getName());
//    }
//
//    @Test
//    public void getStaff_engineerInstantiatesWithStaff_String() {
//        Engineer testEngineer = new Engineer("Henry", "EK6487");
//        assertEquals("EK6487", testEngineer.getStaff());
//    }
//
//    @Test
//    public void equals_returnsTrueIfNameAndStaffAreSame_true() {
//        Engineer firstEngineer = new Engineer("Henry", "EK6487");
//        Engineer anotherEngineer = new Engineer("Henry", "EK6487");
//        assertTrue(firstEngineer.equals(anotherEngineer));
//    }
//
//    @Test
//    public void save_insertsObjectIntoDatabase_Engineer() {
//        Engineer testEngineer = new Engineer("Henry", "EK6487");
//        testEngineer.save();
//        assertTrue(Engineer.all().get(0).equals(testEngineer));
//    }
//
//    @Test
//    public void all_returnsAllInstancesOfEngineer_true() {
//        Engineer firstEngineer = new Engineer("Henry", "EK6487");
//        firstEngineer.save();
//        Engineer secondEngineer = new Engineer("Kerry", "EK6688");
//        secondEngineer.save();
//        assertEquals(true, Engineer.all().get(0).equals(firstEngineer));
//        assertEquals(true, Engineer.all().get(1).equals(secondEngineer));
//    }
//
//    @Test
//    public void save_assignsIdToObject() {
//        Engineer testEngineer = new Engineer("Henry", "EK6487");
//        testEngineer.save();
//        Engineer savedEngineer = Engineer.all().get(0);
//        assertEquals(testEngineer.getId(), savedEngineer.getId());
//    }
//
//    @Test
//    public void find_returnsEngineerWithSameId_secondEngineer() {
//        Engineer firstEngineer = new Engineer("Henry", "EK6487");
//        firstEngineer.save();
//        Engineer secondEngineer = new Engineer("Kerry", "EK6688");
//        secondEngineer.save();
//        assertEquals(Engineer.find(secondEngineer.getId()), secondEngineer);
//    }
//
//}