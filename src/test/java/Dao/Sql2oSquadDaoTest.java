package Dao;

import models.Squad;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.sql.SQLInput;

import static org.junit.Assert.*;

public class Sql2oSquadDaoTest {
    private Sql2oSquadDao squadDao;
    private Sql2oHeroDao heroDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString =  "jdbc:postgresql://localhost:5432/heroSquad_test";
        Sql2o sql2o = new Sql2o(connectionString, "guyo", "@#scorpion");
        squadDao = new Sql2oSquadDao(sql2o);
        heroDao = new Sql2oHeroDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addsSquadCorrectly(){
        Squad squad = setUpNewSquad();
        squadDao.add(squad);
        assertEquals(true, squadDao.getAll().size());
    }

    @Test
    public void addSquadSetId(){
        Squad squad = setUpNewSquad();
        int orginalId = squad.getId();
        squadDao.add(squad);
        assertNotEquals(orginalId, squad.getId());
    }

    @Test
    public void addedSquadsAreReturnedFromGetAll() throws Exception {
        Squad squad = setUpNewSquad();
        squadDao.add(squad);
        assertEquals(1, squadDao.getAll().size());
    }


    // handler
    public Squad setUpNewSquad(){
        return new Squad("Team Flash", 23, "Electricty");
    }
}