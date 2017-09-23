package com.smile24es.ts_project.dao;

import com.smile24es.ts_project.beans.RowData;
import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.hibernate.Query;

/**
 * Created by hasithagamage
 *         on 5/15/17.
 */
@Repository("rowDataDao")
public class RowDataDaoImpl extends AbstractDao<Integer, RowData>  implements RowDataDao {

    private static final Logger SL4J_LOGGER = LoggerFactory.getLogger(RowDataDaoImpl.class);

    @Override
    public List<RowData> findAllRowData() {
        SL4J_LOGGER.info("Starting to run HQL Query to find all row data.");
        Criteria criteria = createEntityCriteria();
        return (List<RowData>) criteria.list();
    }

    @Override
    public void saveRowData(RowData category) {
        SL4J_LOGGER.info("Starting to run HQL Query to save row data.");
        persist(category);
    }

    @Override
    public void deleteAllRowData() {
        Query query = getSession().createSQLQuery("delete from ROW_DATA");
        query.executeUpdate();
    }

   
}
