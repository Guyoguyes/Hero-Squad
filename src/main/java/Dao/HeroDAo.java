package Dao;

import models.Hero;

import java.util.List;

public interface HeroDAo {

    //Get all Heros
    List<Hero> getAll();

    //Create hero
    void add(Hero hero);

    //Find By Id
    Hero findById(int id);

    //Update
    void update(int id, String name, int age, String superPower, String weakness, int squadId);


    // DELETE

    void deleteById(int id);
}
