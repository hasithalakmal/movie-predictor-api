package com.smile24es.ts_project.dao;

import com.smile24es.ts_project.beans.Director;
import com.smile24es.ts_project.beans.DuratonCategory;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

/**
 * Created by hasithagamage on 5/15/17.
 */
public interface DuratonCategoryDao {

    List<DuratonCategory> findAllDuratonCategory();

    void saveDuratonCategory(DuratonCategory duratonCategory);

    DuratonCategory findDuratonCategoryByName(String duratonCategoryName);

    void deleteAllDuratonCategories();
    
}
