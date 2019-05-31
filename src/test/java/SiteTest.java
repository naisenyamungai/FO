import models.Site;
import org.junit.*;
import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

public class SiteTest {
    @Rule
     public DatabaseRule database = new DatabaseRule();


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void site_instantiatesCorrectly_true() {
    Site testMonster = new Site("Mililu", 1);
    assertEquals(true, testMonster instanceof Site);
    }

    @Test
    public void Site_instantiatesWithName_String() {
    Site testSite = new Site("Mililu", 1);
    assertEquals("Mililu", testSite.getName());
   }

    @Test
    public void Site_instantiatesWithPersonId_int() {
    Site testSite = new Site("Mililu", 1);
    assertEquals(1, testSite.getPersonId());
    }

    @Test
    public void equals_returnsTrueIfNameAndPersonIdAreSame_true() {
     Site testSite = new Site("Mililu", 1);
     Site anotherSite = new Site("Mililu", 1);
     assertTrue(testSite.equals(anotherSite));
    }

    @Test
    public void save_returnsTrueIfDescriptionsAretheSame() {
    Site testSite = new Site("Mililu", 1);
    testSite.save();
    assertTrue(Site.all().get(0).equals(testSite));
    }

    @Test
    public void save_assignsIdToSite() {
        Site testSite = new Site("Mililu", 1);
        testSite.save();
        Site savedSite = Site.all().get(0);
        assertEquals(savedSite.getId(), testSite.getId());
    }

    @Test
    public void find_returnsSiteWithSameId_secondSite() {
        Site firstSite = new Site("Mililu", 1);
        firstSite.save();
        Site secondSite = new Site("Ruai", 3);
        secondSite.save();
        assertEquals(Site.find(secondSite.getId()), secondSite);
    }

//    @Test
//    public void save_savesEngineerIdIntoDB_true() {
//        models.Engineer testEngineer = new models.Engineer("Henry", "EK6487");
//        testEngineer.save();
//        models.Site testSite = new models.Site("Mililu", testEngineer.getId());
//        testSite.save();
//        models.Site savedSite = models.Site.find(testSite.getId());
//        assertEquals(savedSite.getEngineerId(), testEngineer.getId());
//    }


}