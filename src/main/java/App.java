import static spark.Spark.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import dao.Sql2oEngineerDao;
import dao.Sql2oSiteDao;
import models.Engineer;
import models.Site;

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
        Sql2oSiteDao siteDao = new Sql2oSiteDao(sql2o);

        get("/", (request, response) -> {
                    Map<String, Object> model = new HashMap<>();
                    return new ModelAndView(model, "welcome.hbs");
                }, new HandlebarsTemplateEngine()
        );

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
        get("/index", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Engineer> allEngineers = engineerDao.getAll();
            model.put("engineers", allEngineers);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());


        //get: show new engineer form
        get("/engineers/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Engineer> engineers = engineerDao.getAll();
            return new ModelAndView(model, "engineer-form.hbs");
        }, new HandlebarsTemplateEngine());


        //engineer: process new engineer form
        post("/engineers", (req, res) -> { //URL to make new task on POST route
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            String staff = req.queryParams("staff");
            String status = req.queryParams("status");
            String last_name = req.queryParams("last_name");
            String first_name = req.queryParams("first_name");
            Engineer newEngineer = new Engineer(staff, first_name, name, last_name,  status);
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
        get("/engineers/:id/edit", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("editEngineer", true);
            int idOfEngineerToEdit = Integer.parseInt(req.params("id"));
            Engineer editEngineer = engineerDao.findById(idOfEngineerToEdit);
            model.put("editEngineer", editEngineer);
            return new ModelAndView(model, "engineer-form.hbs");
        }, new HandlebarsTemplateEngine());


        //engineer: process a form to update an engineer
        post("/engineers/:id", (req, res) -> { //URL to update task on POST route
            Map<String, Object> model = new HashMap<>();
            int idOfEngineerToEdit = Integer.parseInt(req.params("id"));
            String newEngineer = req.queryParams("name");
            String status= req.queryParams("status");
            engineerDao.update(idOfEngineerToEdit, newEngineer, status);
            res.redirect("index.hbs");
            return null;
        }, new HandlebarsTemplateEngine());



        // site Details


        //get: delete all sites
        get("/sites/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            siteDao.clearAllSites(); //change
            res.redirect("site.hbs");
            return null;
        }, new HandlebarsTemplateEngine());


        //get: delete a site
        get("/sites/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSiteToDelete = Integer.parseInt(req.params("id"));
            siteDao.deleteById(idOfSiteToDelete); //change
            res.redirect("site.hbs");
            return null;
        }, new HandlebarsTemplateEngine());


        //get: show all sites
        get("/site", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Site> sites = siteDao.getAll();
            model.put("sites", sites);
            return new ModelAndView(model, "site.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show new Site form
        get("/sites/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Engineer> engineers = engineerDao.getAll();
            model.put("engineers", engineers);
            return new ModelAndView(model, "site-form.hbs");
        }, new HandlebarsTemplateEngine());


        //site: process new site form
        post("/sites", (req, res) -> { //URL to make new task on POST route
            Map<String, Object> model = new HashMap<>();
            List<Engineer> allEngineers = engineerDao.getAll();
            model.put("engineers", allEngineers);
            String node_name = req.queryParams("node_name");
            String site_id = req.queryParams("site_id");
            String site_name = req.queryParams("site_name");
            String technology = req.queryParams("technology");
            String status = req.queryParams("status");
            int engineerId = Integer.parseInt(req.queryParams("engineerId"));
            Site newSite = new Site(node_name, site_id, site_name, technology, status, engineerId);
            siteDao.add(newSite);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());


        get("/engineers/:engineer_id/sites/:site_id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSiteToFind = Integer.parseInt(req.params("site_id"));
            Site foundSite = siteDao.findById(idOfSiteToFind);
            int idOfEngineerToFind = Integer.parseInt(req.params("engineer_id"));
            Engineer foundEngineer = engineerDao.findById(idOfEngineerToFind);
            model.put("site", foundSite);
            model.put("engineer", foundEngineer);
            model.put("engineers", engineerDao.getAll());
            return new ModelAndView(model, "engineer-detail.hbs");
        }, new HandlebarsTemplateEngine());


        //get: show an individual site
        get("/sites/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSiteToFind = Integer.parseInt(req.params("id")); //pull id - must match route segment
            Site foundSite = siteDao.findById(idOfSiteToFind); //use it to find task
            model.put("site", foundSite); //add it to model for template to display
            return new ModelAndView(model, "site-detail.hbs"); //individual task page.
        }, new HandlebarsTemplateEngine());


        get("/sites/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSiteToFind = Integer.parseInt(req.params("id")); //pull id - must match route segment
            Site foundSite = siteDao.findById(idOfSiteToFind); //use it to find task
            model.put("site", foundSite); //add it to model for template to display
            return new ModelAndView(model, "engineer-detail"); //individual task page.
        }, new HandlebarsTemplateEngine());




        //get: show a form to update an Engineer
        get("/sites/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSiteToEdit = Integer.parseInt(req.params("id"));
            Site editSite = siteDao.findById(idOfSiteToEdit);
            model.put("editSite", editSite);
            return new ModelAndView(model, "site-form.hbs");
        }, new HandlebarsTemplateEngine());


        //site: process a form to update a site
        post("/sites/:id", (req, res) -> { //URL to update task on POST route
            Map<String, Object> model = new HashMap<>();
            String newSite = req.queryParams("site_name");
            String status= req.queryParams("status");
            int idOfSiteToEdit = Integer.parseInt(req.params("id"));
            siteDao.update(idOfSiteToEdit, newSite, status);
            res.redirect("/site");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: delete all Engineers and all sites
        get("/engineers/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            engineerDao.clearAllEngineers();
            siteDao.clearAllSites();
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: show an individual Engineer and sites
        get("/engineers/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfEngineerToFind = Integer.parseInt(req.params("id")); //new
            Engineer foundEngineer = engineerDao.findById(idOfEngineerToFind);
            model.put("engineer", foundEngineer);
            List<Site> allSitesByEngineer = engineerDao.getAllSitesByEngineer(idOfEngineerToFind);
            model.put("sites", allSitesByEngineer);
            model.put("engineers", engineerDao.getAll());
            return new ModelAndView(model, "engineer-detail.hbs");
        }, new HandlebarsTemplateEngine());


        //get: show all sites under all engineers and show all Engineers
        get("/index", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Engineer> allEngineers = engineerDao.getAll();
            model.put("engineers", allEngineers);
            List<Site> sites = siteDao.getAll();
            model.put("sites", sites);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());




    }
}