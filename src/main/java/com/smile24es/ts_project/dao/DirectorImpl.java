package com.smile24es.ts_project.dao;

import com.smile24es.ts_project.beans.Actor;
import com.smile24es.ts_project.beans.Director;
import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

/**
 * Created by hasithagamage
 *         on 5/15/17.
 */
@Repository("directorDao")
public class DirectorImpl extends AbstractDao<Integer, Director>  implements DirectorDao {

    private static final Logger SL4J_LOGGER = LoggerFactory.getLogger(DirectorImpl.class);

    @Override
    public List<Director> findAllDirectors() {
        SL4J_LOGGER.info("Starting to run HQL Query to find all row data.");
        Criteria criteria = createEntityCriteria();
        return (List<Director>) criteria.list();
    }

    @Override
    public void saveDirector(Director director) {
        SL4J_LOGGER.info("Starting to run HQL Query to save row data.");
        persist(director);
    }

    @Override
    public Director findDirectorByName(String directorName) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("directorName", directorName));
        return (Director) criteria.uniqueResult();
    }

    @Override
    public void deleteAllDirectors() {
        Query query = getSession().createSQLQuery("delete from DIRECTOR");
        query.executeUpdate();
    }
   
}
