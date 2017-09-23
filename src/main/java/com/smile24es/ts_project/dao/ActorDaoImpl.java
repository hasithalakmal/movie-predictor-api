package com.smile24es.ts_project.dao;

import com.smile24es.ts_project.beans.Actor;
import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

/**
 * Created by hasithagamage on 5/15/17.
 */
@Repository("actorDao")
public class ActorDaoImpl extends AbstractDao<Integer, Actor> implements ActorDao {

    private static final Logger SL4J_LOGGER = LoggerFactory.getLogger(ActorDaoImpl.class);

    @Override
    public List<Actor> findAllActors() {
        SL4J_LOGGER.info("Starting to run HQL Query to find all row data.");
        Criteria criteria = createEntityCriteria();
        return (List<Actor>) criteria.list();
    }

    @Override
    public void saveActor(Actor actor) {
        SL4J_LOGGER.info("Starting to run HQL Query to save row data.");
        persist(actor);
    }

    @Override
    public Actor findActorByName(String actorName) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("actorName", actorName));
        return (Actor) criteria.uniqueResult();
    }

    @Override
    public void deleteAllActors() {
        Query query = getSession().createSQLQuery("delete from ACTOR");
        query.executeUpdate();
    }

}
