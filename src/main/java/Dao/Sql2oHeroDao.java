package Dao;

import models.Hero;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oHeroDao implements HeroDAo{
    private final Sql2o sql2o;

    public Sql2oHeroDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public List<Hero> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM heroes")
                    .executeAndFetch(Hero.class);
        }
    }

    @Override
    public void add(Hero hero) {
        String sql = "INSERT INTO heroes (name, age, superPower, weakness, squadId) VALUES (:name, :age, :superPower, :weakness, :squadId)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(hero)
                    .executeUpdate()
                    .getKey();
            hero.setId(id);
        }catch (Sql2oException exception){
            System.out.println(exception);
        }
    }

    @Override
    public Hero findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM heroes WHERE id=:id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Hero.class);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM heroes WHERE id=:id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }catch (Sql2oException exception){
            System.out.println(exception);
        }
    }

    @Override
    public void update(int id, String name, int age, String superPower, String weakness, int squadId) {
        String sql = "UPDATE heroes SET name = :name, " +
                "age = :age, superPower = :superPower, " +
                "weakness = :weakness, squadId = :squadId WHERE id=:id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("age", age)
                    .addParameter("superPower", superPower)
                    .addParameter("weakness", weakness)
                    .addParameter("squadId", squadId)
                    .addParameter("id", id)
                    .executeUpdate();
        }catch(Sql2oException exception){
            System.out.println(exception);
        }

    }
}
