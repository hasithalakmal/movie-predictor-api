package com.smile24es.ts_project.dao;


import com.smile24es.ts_project.beans.Director;
import java.util.List;

/**
 * Created by hasithagamage on 5/15/17.
 */
public interface DirectorDao {

    List<Director> findAllDirectors();

    void saveDirector(Director director);

    Director findDirectorByName(String directorName);

    void deleteAllDirectors();
}
