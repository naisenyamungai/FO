import org.sql2o.*;

import java.util.ArrayList;
import java.util.List;



public class Engineer  {
    private String name;
    private String staff;
    private int id;

    public Engineer(String name, String staff) {
        this.name = name;
        this.staff = staff;
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


    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO engineers (name, staff) VALUES (:name, :staff)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("staff", this.staff)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Engineer> all() {
        String sql = "SELECT * FROM engineers";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Engineer.class);
        }
    }

    public static Engineer find(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM engineers where id=:id";
            Engineer engineer = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Engineer.class);
            return engineer;
        }
    }
}