package dao;

import models.Engineer;
import java.util.List;

public interface EngineerDao {
    List<Engineer> getAll();

    void add(Engineer engineer);

    Engineer findById(int id);

    void update(int id, String name);

    void deleteById(int id);

    void clearAllEngineers();

}
