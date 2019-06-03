package models;

import org.sql2o.*;

import java.util.List;



public class Engineer{
    private String name;
    private String staff;
    private String first_name;
    private String last_name;
    private String status;
    private int id;

    public Engineer(String name, String staff) {
        this.name = name;
        this.staff = staff;
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


    @Override
    public boolean equals(Object otherEngineer) {
        if (!(otherEngineer instanceof Engineer)) {
            return false;
        } else {
            Engineer newEngineer = (Engineer) otherEngineer;
            return this.getName().equals(newEngineer.getName()) &&
                    this.getStaff().equals(newEngineer.getStaff());
        }
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