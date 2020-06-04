import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Dao.Sql2oHeroDao;
import Dao.Sql2oSquadDao;
import models.Hero;
import models.Squad;
import org.sql2o.Sql2o;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.ModelAndView;

import static spark.Spark.*;


public class App {
//    static int getHerokuAssignedPort() {
//        ProcessBuilder processBuilder = new ProcessBuilder();
//        if (processBuilder.environment().get("PORT") != null) {
//            return Integer.parseInt(processBuilder.environment().get("PORT"));
//        }
//        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
//    }
    public static void main(String[] args) {
        ProcessBuilder process = new ProcessBuilder();
        Integer port;

        // This tells our app that if Heroku sets a port for us, we need to use that port.
        // Otherwise, if they do not, continue using port 4567.

        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }

        port(port);


        staticFileLocation("/public");


        String connectionString =  "jdbc:postgresql://syuacufecjrmrn:5f3c1d44325fba438b5d5c864b5f996ecd9cffe45877a1dfbd3855a199376a59@ec2-52-202-146-43.compute-1.amazonaws.com:5432/df8mqj9fu86b5n";
        Sql2o sql2o = new Sql2o(connectionString, "syuacufecjrmrn", "5f3c1d44325fba438b5d5c864b5f996ecd9cffe45877a1dfbd3855a199376a59");
        Sql2oHeroDao heroDao = new Sql2oHeroDao(sql2o);
        Sql2oSquadDao squadDao = new Sql2oSquadDao(sql2o);

        //home
        get("/", (req, res) ->{
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //create hero form
        get("/hero/form", (req, res) ->{
            Map<String, Object> model = new HashMap<>();
            List<Hero> heroes = heroDao.getAll();
            List<Squad> squads = squadDao.getAll();
            model.put("heroes", heroes);
            model.put("squads", squads);
            return new ModelAndView(model, "hero-form.hbs");
        }, new HandlebarsTemplateEngine());

        //process created hero
        post("/heroes", (req, res) ->{
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            int age = Integer.parseInt(req.queryParams("age"));
            String superPower = req.queryParams("superPower");
            String weakness = req.queryParams("weakness");
            Hero newHero = new Hero(name, age, superPower, weakness, 1);
            heroDao.add(newHero);
            List<Hero> heroes = heroDao.getAll();
            List<Squad> squads = squadDao.getAll();
            model.put("heroes", heroes);
            model.put("squads", squads);
            return new ModelAndView(model, "heroes.hbs");
        }, new HandlebarsTemplateEngine());

        //get all heroes
        get("/heroes", (req, res) ->{
            Map<String, Object> model = new HashMap<>();
            List<Hero> heroes = heroDao.getAll();
            model.put("heroes", heroes);
            return new ModelAndView(model, "heroes.hbs");
        }, new HandlebarsTemplateEngine());

        //get individual hero
        get("/heroes/:id", (req, res) ->{
            Map<String, Object> model = new HashMap<>();
            int heroId = Integer.parseInt(req.params("id"));
            Hero hero = heroDao.findById(heroId);
            int squadId = hero.getSquadId();
            Squad squad = squadDao.findById(squadId);
            model.put("hero", hero);
            model.put("squad", squad);
            List<Hero> heroes = heroDao.getAll();
            model.put("heroes", heroes);
            return new ModelAndView(model, "hero-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //delete individual hero
        get("/heroes/:id/delete", (req, res) ->{
            Map<String, Object> model = new HashMap<>();
            int heroId = Integer.parseInt(req.params("id"));
            heroDao.deleteById(heroId);
            List<Hero> heroes = heroDao.getAll();
            model.put("heroes", heroes);
            return new ModelAndView(model, "heroes.hbs");
        }, new HandlebarsTemplateEngine());

        //update individual hero
        get("/heroes/:id/edit", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Squad> squads = squadDao.getAll();
            int idOfHeroToEdit = Integer.parseInt(req.params("id"));
            Hero editHero = heroDao.findById(idOfHeroToEdit);
            model.put("squads",squads);
            model.put("hero", editHero);
            model.put("editHero",true);

            return new ModelAndView(model, "hero-form.hbs");
        }, new HandlebarsTemplateEngine());

        //process updated form
        post("/heroes/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            int idOfHero = Integer.parseInt(req.params("id"));
            int age= Integer.parseInt(req.queryParams("age"));
            String superPower =req.queryParams("superPower");
            String weakness=req.queryParams("weakness");
            int squadId = Integer.parseInt(req.queryParams("squadId"));
            heroDao.update(idOfHero, name, age, superPower, weakness, squadId);
            List<Hero> heroes = heroDao.getAll();
            model.put("heroes", heroes);
            Squad squad = squadDao.findById(squadId);
            Hero hero = heroDao.findById(idOfHero);
            model.put("hero", hero);
            model.put("squad", squad);
            return new ModelAndView(model,"hero-detail.hbs");
        }, new HandlebarsTemplateEngine());

        // create squad form
        get("/squad-form", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Squad> squads = squadDao.getAll();
            model.put("squads", squads);
            return new ModelAndView(model, "squad-form.hbs");
        }, new HandlebarsTemplateEngine());

        //process new squad
        post("/squads", (req, res) -> { //new
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            int size = Integer.parseInt(req.queryParams("size"));
            String source = req.queryParams("source");
            Squad squad = new Squad(name, size, source);
            squadDao.add(squad);
            List<Squad> squads = squadDao.getAll();
            model.put("squads",squads);
            return new ModelAndView(model,"squads.hbs");
        }, new HandlebarsTemplateEngine());

        //get all squads
        get("/squads",(req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Squad> squads = squadDao.getAll();
            model.put("squads",squads);
            return new ModelAndView(model, "squads.hbs");
        },new HandlebarsTemplateEngine());

        //Individual Squad detail
        get("/squad/:id",(request, response) ->{
            Map<String, Object> model = new HashMap<>();
            int squadId =Integer.parseInt(request.params("id"));
            List<Hero> heroes = squadDao.getAllHeroesBySquad(squadId);
            Squad squads = squadDao.findById(squadId);
            List<Squad> squad =squadDao.getAll();
            model.put("squad",squads);
            model.put("heroes", heroes);
//            model.put("heroes",heroes);
            return new ModelAndView(model,"squad-details.hbs");
        },new HandlebarsTemplateEngine());

        //update individual squad
        get("/squad/:id/edit", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Squad> squads = squadDao.getAll();
            int squadId = Integer.parseInt(req.params("id"));
            Squad squad = squadDao.findById(squadId);
            model.put("squad",squad);
            model.put("squads",squads);
            model.put("editSquad",true);
            return new ModelAndView(model, "squad-form.hbs");
        }, new HandlebarsTemplateEngine());

        // process a form to update
        post("/squad/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            int id = Integer.parseInt(req.params("id"));
            int squadId = Integer.parseInt(req.params("id"));
            int size= Integer.parseInt(req.queryParams("size"));
            String source=req.queryParams("source");;
            squadDao.update(id, name, size, source);
            List<Squad> squads = squadDao.getAll();
            model.put("heroes", squads);
            Squad squad = squadDao.findById(squadId);
            model.put("squad", squad);
            model.put("editSquad",true);

            return new ModelAndView(model,"squad-details.hbs");
        }, new HandlebarsTemplateEngine());

        //delete individual squad
        get("/squad/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int squadId = Integer.parseInt(req.params("id"));
            squadDao.deleteById(squadId);
            List<Squad> squads = squadDao.getAll();
            model.put("squads", squads);
            return new ModelAndView(model,"squads.hbs");

        }, new HandlebarsTemplateEngine());

    }
}
