package dao;
import models.Engineer;
import org.sql2o.*;
import java.util.List;

public class Sql2oEngineerDao implements EngineerDao{
    private final Sql2o sql2o;

    public Sql2oEngineerDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Engineer engineer) {
        String sql = "INSERT INTO engineers (name, staff, first_name, last_name, status) VALUES (:name, :staff, :first_name, :last_name, :status)"; //raw sql
        try(Connection con = sql2o.open()){ //try to open a connection
            int id = (int) con.createQuery(sql, true) //make a new variable
                    .bind(engineer) //map my argument onto the query so we can use information from it
                    .executeUpdate() //run it all
                    .getKey(); //int id is now the row number (row “key”) of db
            engineer.setId(id); //update object to set id now from database
        } catch (Sql2oException ex) {
            System.out.println(ex); //oops we have an error!
        }
    }

    @Override
    public List<Engineer> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM engineers") //raw sql
                    .executeAndFetch(Engineer.class); //fetch a list
        }
    }

    @Override
    public Engineer findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM engineers WHERE id = :id")
                    .addParameter("id", id) //key/value pair, key must match above
                    .executeAndFetchFirst(Engineer.class); //fetch an individual item
        }
    }

    @Override
    public void update(int id, String newName, String status){
        String sql = "UPDATE engineers SET name = :name WHERE id=:id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("name", newName)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from engineers WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }


    @Override
    public void clearAllEngineers() {
        String sql = "DELETE from engineers";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

}
