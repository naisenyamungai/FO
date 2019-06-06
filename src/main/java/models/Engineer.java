package models;

import org.sql2o.*;
import java.util.List;
import java.util.Objects;
import java.util.ArrayList;


public class Engineer{
    private String name;
    private String staff;
    private String first_name;
    private String last_name;
    private String status;
    private int id;
    private ArrayList<Site> sites;


    public Engineer(String staff, String first_name, String name, String last_name, String status) {
        this.name = name;
        this.staff = staff;
        this.status = status;
        this.first_name = first_name;
        this.last_name = last_name;
    }


    public void setName(String name){
        this.name = name;
    }

    public void setStaff(String Staff){
        this.staff = staff;
    }

    public void setFirst_name(String first_name){this.first_name = first_name;}

    public void setLast_name(String last_name){this.last_name = last_name;}

    public void setStatus(String status){this.status = status;}

    public void setId(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getStaff() {
        return staff;
    }

    public int getId() {
        return id;
    }

    public String getFirst_name(){ return first_name; }

    public String getLast_name(){ return last_name;}

    public String getStatus(){ return status;}


//    @Override
//    public boolean equals(Object otherEngineer) {
//        if (!(otherEngineer instanceof Engineer)) {
//            return false;
//        } else {
//            Engineer newEngineer = (Engineer) otherEngineer;
//            return this.getName().equals(newEngineer.getName()) &&
//                    this.getStaff().equals(newEngineer.getStaff());
//        }
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Engineer engineer = (Engineer) o;
        return getId() == engineer.getId() &&
                Objects.equals(getName(), engineer.getName()) &&
                Objects.equals(getStaff(), engineer.getStaff()) &&
                Objects.equals(getStatus(), engineer.getStatus()) &&
                Objects.equals(getFirst_name(), engineer.getFirst_name()) &&
                Objects.equals(getLast_name(), engineer.getLast_name()) &&
                Objects.equals(sites, engineer.sites);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getId(), getStaff(), getStatus(), getFirst_name(), getLast_name(), sites);
    }
//
//
//    public void save() {
//        try (Connection con = DB.sql2o.open()) {
//            String sql = "INSERT INTO engineers (name, staff) VALUES (:name, :staff)";
//            this.id = (int) con.createQuery(sql, true)
//                    .addParameter("name", this.name)
//                    .addParameter("staff", this.staff)
//                    .executeUpdate()
//                    .getKey();
//        }
//    }
//
//    public static List<Engineer> all() {
//        String sql = "SELECT * FROM engineers";
//        try (Connection con = DB.sql2o.open()) {
//            return con.createQuery(sql).executeAndFetch(Engineer.class);
//        }
//    }
//
//    public static Engineer find(int id) {
//        try (Connection con = DB.sql2o.open()) {
//            String sql = "SELECT * FROM engineers where id=:id";
//            Engineer engineer = con.createQuery(sql)
//                    .addParameter("id", id)
//                    .executeAndFetchFirst(Engineer.class);
//            return engineer;
//        }
//    }
//
//    public List<Site> getSites() {
//        try (Connection con = DB.sql2o.open()) {
//            String sql = "SELECT * FROM sites WHERE personId=:id";
//            return con.createQuery(sql)
//                    .addParameter("id", this.id)
//                    .executeAndFetch(Site.class);
//        }
//    }
//
//
//    public void delete() {
//        try (Connection con = DB.sql2o.open()) {
//            String sql = "DELETE FROM engineers WHERE id = :id;";
//            con.createQuery(sql)
//                    .addParameter("id", this.id)
//                    .executeUpdate();
//        }
//    }

}