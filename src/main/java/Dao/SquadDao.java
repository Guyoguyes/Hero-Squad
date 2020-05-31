package Dao;

import models.Hero;
import models.Squad;

import java.util.List;

public interface SquadDao {

    //Get All squad
    List<Squad> getAll();

    //Add Squad
    void add(Squad squad);

    // Find by id
    Squad findById(int id);

    //update squad
    void update(int id, String name, int size, String source);

    //DELETE
    void deleteById(int id);

    List<Hero> getAllHeroesBySquad(int squadId);
}
