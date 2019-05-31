package dao;
import models.Engineer;

import org.junit.After;
import org.junit.Before;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class EngineerDaoTest {
    private Sql2oEngineerDao engineerDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        engineerDao = new Sql2oEngineerDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingEngineerId() throws Exception {
        Engineer engineer = new Engineer("Henry", "EK6487");
        int originalEngineerId = engineer.getId();
        engineerDao.add(engineer);
        assertNotEquals(originalEngineerId, engineer.getId());
    }

    @Test
    public void existingEngineersCanBeFoundById() throws Exception {
        Engineer engineer = new Engineer("Henry", "EK6487");
        Engineer foundEngineer = engineerDao.findById(engineer.getId());
        assertEquals(engineer, foundEngineer);
    }
}