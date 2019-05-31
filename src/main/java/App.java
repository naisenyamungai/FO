import static spark.Spark.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import dao.Sql2oEngineerDao;
import models.Engineer;

import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class App{
    public static void main(String[] args){
        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }

        port(port);


        staticFileLocation("/public");
        Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/site_maintenance", null, null);
//        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oEngineerDao engineerDao = new Sql2oEngineerDao(sql2o);


    //get: delete all engineers
        get("/engineers/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            engineerDao.clearAllEngineers(); //change
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());


        //get: delete an individual engineer
        get("/engineers/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfEngineerToDelete = Integer.parseInt(req.params("id"));
            engineerDao.deleteById(idOfEngineerToDelete); //change
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());


        //get: show all engineers
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Engineer> engineers = engineerDao.getAll();
            model.put("engineers", engineers);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show new engineer form
        get("/engineers/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "engineer-form.hbs");
        }, new HandlebarsTemplateEngine());


        //engineer: process new engineer form
        post("/engineers", (req, res) -> { //URL to make new task on POST route
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            String staff = req.queryParams("staff");
            Engineer newEngineer = new Engineer(name, staff);
            engineerDao.add(newEngineer);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: show an individual engineer
        get("/engineers/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfEngineerToFind = Integer.parseInt(req.params("id")); //pull id - must match route segment
            Engineer foundEngineer = engineerDao.findById(idOfEngineerToFind); //use it to find task
            model.put("engineer", foundEngineer); //add it to model for template to display
            return new ModelAndView(model, "engineer-detail.hbs"); //individual task page.
        }, new HandlebarsTemplateEngine());


        //get: show a form to update an Engineer
        get("/engineers/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfEngineerToEdit = Integer.parseInt(req.params("id"));
            Engineer editEngineer = engineerDao.findById(idOfEngineerToEdit);
            model.put("editEngineer", editEngineer);
            return new ModelAndView(model, "engineer-form.hbs");
        }, new HandlebarsTemplateEngine());


        //engineer: process a form to update an engineer
        post("/engineers/:id", (req, res) -> { //URL to update task on POST route
            Map<String, Object> model = new HashMap<>();
            String newEngineer = req.queryParams("name");
            int idOfEngineerToEdit = Integer.parseInt(req.params("id"));
            engineerDao.update(idOfEngineerToEdit, newEngineer);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());


    }
}