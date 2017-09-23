package com.smile24es.ts_project.dao;

import com.smile24es.ts_project.beans.Director;
import com.smile24es.ts_project.beans.DuratonCategory;
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
@Repository("duratonCategoryDao")
public class DuratonCategoryImpl extends AbstractDao<Integer, DuratonCategory>  implements DuratonCategoryDao {

    private static final Logger SL4J_LOGGER = LoggerFactory.getLogger(DuratonCategoryImpl.class);

    @Override
    public List<DuratonCategory> findAllDuratonCategory() {
        SL4J_LOGGER.info("Starting to run HQL Query to find all row data.");
        Criteria criteria = createEntityCriteria();
        return (List<DuratonCategory>) criteria.list();
    }

    @Override
    public void saveDuratonCategory(DuratonCategory duratonCategory) {
        SL4J_LOGGER.info("Starting to run HQL Query to save row data.");
        persist(duratonCategory);
    }

   @Override
    public DuratonCategory findDuratonCategoryByName(String duratonCategoryName) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("duratonCategoryName", duratonCategoryName));
        return (DuratonCategory) criteria.uniqueResult();
    }

    @Override
    public void deleteAllDuratonCategories() {
        Query query = getSession().createSQLQuery("delete from DURATON_CATEGORY");
        query.executeUpdate();
    }
}
