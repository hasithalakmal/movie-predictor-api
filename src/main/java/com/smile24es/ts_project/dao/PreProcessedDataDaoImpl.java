package com.smile24es.ts_project.dao;

import com.smile24es.ts_project.beans.Actor;
import com.smile24es.ts_project.beans.PreProcessedData;
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
@Repository("preProcessedDataDao")
public class PreProcessedDataDaoImpl extends AbstractDao<Integer, PreProcessedData>  implements PreProcessedDataDao {

    private static final Logger SL4J_LOGGER = LoggerFactory.getLogger(PreProcessedDataDaoImpl.class);

    @Override
    public List<PreProcessedData> findAllPreProcessedData() {
        SL4J_LOGGER.info("Starting to run HQL Query to find all pre processed data.");
        Criteria criteria = createEntityCriteria();
        return (List<PreProcessedData>) criteria.list();
    }

    @Override
    public void savePreProcessedData(PreProcessedData preProcessedData) {
        SL4J_LOGGER.info("Starting to run HQL Query to save pre processed data.");
        persist(preProcessedData);
    }

   @Override
    public PreProcessedData findPreProcessedDataByName(String moveName) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("moveName", moveName));
        return (PreProcessedData) criteria.uniqueResult();
    }

    @Override
    public void deleteAllPreProcessedData() {
        Query query = getSession().createSQLQuery("delete from PRE_PROCESSED_DATA");
        query.executeUpdate();
    }
}
