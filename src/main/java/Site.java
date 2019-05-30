import org.sql2o.*;

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
            String sql = "INSERT INTO sitess (name, personid) VALUES (:name, :personId)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("personId", this.personId)
                    .executeUpdate()
                    .getKey();
        }
    }


}