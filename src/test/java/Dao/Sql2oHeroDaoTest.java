package Dao;

import models.Hero;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oHeroDaoTest {
    private Sql2oHeroDao heroDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        heroDao = new Sql2oHeroDao(sql2o); //ignore me for now
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void heroInstantesWithId() throws Exception{
        Hero hero = setupNewHero();
        int id = hero.getId();
        heroDao.add(hero);
        assertNotEquals(id, hero.getId());
    }

    @Test
    public void heroesAreReturnedAll() throws Exception{
        Hero hero = setupNewHero();
        Hero otherHero = new Hero("BatMan", 23, "stregth", "Fire", 1);
        heroDao.add(hero);
        heroDao.add(otherHero);
        assertEquals(2, heroDao.getAll().size());
    }

    @Test
    public void heroesCanBeFoundById() throws Exception{
        Hero hero = setupNewHero();
        heroDao.add(hero);
        Hero found = heroDao.findById(hero.getId());
        assertEquals(hero, found);
    }

    @Test
    public void updateChangesHeroContent() throws Exception {
        String initialName = "Iron Fist";
        Hero hero = setupNewHero();
        heroDao.add(hero);

        heroDao.update(hero.getId(),"Luke Cage", 40,"bullet proof","emotional",1);
        Hero updatedHero = heroDao.findById(hero.getId());
        assertNotEquals(initialName, updatedHero.getName());
    }

    //helper method
    public Hero setupNewHero(){
        return new Hero("SuperMan",37,"Streght","Krypton", 1);
    }
}