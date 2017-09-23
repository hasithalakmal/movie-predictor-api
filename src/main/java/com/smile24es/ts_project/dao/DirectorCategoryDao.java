package com.smile24es.ts_project.dao;


import com.smile24es.ts_project.beans.DirectorCategory;
import java.util.List;

/**
 * Created by hasithagamage on 5/15/17.
 */
public interface DirectorCategoryDao {

    List<DirectorCategory> findAllDirectorCategory();

    void saveDirectorCategory(DirectorCategory directorCategory);

}
