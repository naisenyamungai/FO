package dao;

import models.Site;
import java.util.List;

public interface SiteDao {
    List<Site> getAll();

    void add(Site site);

    Site findById(int id);

    void update(int id, String site_name, String status);

    void deleteById(int id);

    void clearAllSites();

}
