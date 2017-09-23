package com.smile24es.ts_project.dao;

import com.smile24es.ts_project.beans.DirectorCategory;
import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hasithagamage
 *         on 5/15/17.
 */
@Repository("directorCategoryDao")
public class DirectorCategoryImpl extends AbstractDao<Integer, DirectorCategory>  implements DirectorCategoryDao {

    private static final Logger SL4J_LOGGER = LoggerFactory.getLogger(DirectorCategoryImpl.class);

    @Override
    public List<DirectorCategory> findAllDirectorCategory() {
        SL4J_LOGGER.info("Starting to run HQL Query to find all row data.");
        Criteria criteria = createEntityCriteria();
        return (List<DirectorCategory>) criteria.list();
    }

    @Override
    public void saveDirectorCategory(DirectorCategory directorCategory) {
        SL4J_LOGGER.info("Starting to run HQL Query to save row data.");
        persist(directorCategory);
    }

   
}
