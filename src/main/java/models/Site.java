package models;

import org.sql2o.*;

import java.util.List;

public class Site{
    private String name;
    private int personId;
    private int id;

    public Site(String name, int personId) {
        this.name = name;
        this.personId = personId;
   }

    public String getName(){
        return name;
    }

    public int getPersonId(){
        return personId;
    }

    public int getId(){
        return id;
    }

    @Override
    public boolean equals(Object otherSite){
        if (!(otherSite instanceof Site)) {
            return false;
        } else {
            Site newSite = (Site) otherSite;
            return this.getName().equals(newSite.getName()) &&
                    this.getPersonId() == newSite.getPersonId();
        }
    }



    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sites (name, personid) VALUES (:name, :personId)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("personId", this.personId)
                    .executeUpdate()
                    .getKey();
        }
    }

   public static List<Site> all() {
        String sql = "SELECT * FROM sites";
        try(Connection con = DB.sql2o.open()) {
           return con.createQuery(sql).executeAndFetch(Site.class);
        }
    }


    public static Site find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sites where id=:id";
            Site site = con.createQuery(sql)
                .addParameter("id", id)
                .executeAndFetchFirst(Site.class);
            return site;
         }
      }


      public void delete() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM sites WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeUpdate();
        }
    }


}