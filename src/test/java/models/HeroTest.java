package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HeroTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void hero_newHeroObjectCorrectlyCreated_true(){
        Hero hero = setUpHero();
        assertEquals(true, hero instanceof Hero);
    }

    @Test
    public void hero_HeroInstantiatesWithInputs_true(){
        Hero hero = setUpHero();
        assertEquals("superMan", hero.getName());
    }

//    @Test
//    public void hero_returnsAllHero_true(){
//        Hero hero = setUpHero();
//        Hero otherHero = new Hero("BatMan", 12, "fight", "snake");
//        assertEquals(3, Hero.getAll().size());
//    }
//
//    @Test
//    public void hero_AllHeroContainsAllHero_true(){
//        Hero hero = setUpHero();
//        Hero otherHero = new Hero("BatMan",  12, "fight", "snake");
//        assertTrue(Hero.getAll().contains(hero));
//        assertTrue(Hero.getAll().contains(otherHero));
//    }
//
//    @Test
//    public void hero_InstantecsWithId_true(){
//        Hero hero = setUpHero();
//        assertEquals(1, hero.getId());
//    }
//
//    @Test
//    public void hero_getId_HeroInstantecsWithUniqueId_1(){
//        Hero.clearAllHeros();
//        Hero hero = setUpHero();
//        assertEquals(1, hero.getId());
//    }
//
//    @Test
//    public void hero_findReturnsCorrectHero(){
//        Hero hero = setUpHero();
//        assertEquals(1, Hero.findById(hero.getId()));
//    }
//
//    @Test
//    public void hero_findReturnsCorrectHeroWhenMoreThanOne(){
//        Hero hero = setUpHero();
//        Hero otherHero = new Hero("batman",  12, "fight", "snake");
//        assertEquals(2, Hero.findById(otherHero.getId()).getId());
//    }

    //helper method
    public Hero setUpHero(){
        return new Hero("superMan",  12, "fight", "snake", 1);
    }
}
