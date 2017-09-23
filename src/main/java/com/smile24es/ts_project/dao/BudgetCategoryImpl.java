package com.smile24es.ts_project.dao;

import com.smile24es.ts_project.beans.Actor;
import com.smile24es.ts_project.beans.BudgetCategory;
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
@Repository("budgetCategoryDao")
public class BudgetCategoryImpl extends AbstractDao<Integer, BudgetCategory>  implements BudgetCategoryDao {

    private static final Logger SL4J_LOGGER = LoggerFactory.getLogger(BudgetCategoryImpl.class);

    @Override
    public List<BudgetCategory> findAllBudgetCategory() {
        SL4J_LOGGER.info("Starting to run HQL Query to find all row data.");
        Criteria criteria = createEntityCriteria();
        return (List<BudgetCategory>) criteria.list();
    }

    @Override
    public void saveBudgetCategory(BudgetCategory budgetCategory) {
        SL4J_LOGGER.info("Starting to run HQL Query to save row data.");
        persist(budgetCategory);
    }

   @Override
    public BudgetCategory findBudgetCategoryByName(String budgetCategoryName) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("budgetCategoryName", budgetCategoryName));
        return (BudgetCategory) criteria.uniqueResult();
    }

    @Override
    public void deleteAllBudgetCategories() {
        Query query = getSession().createSQLQuery("delete from BUDGET_CATEGORY");
        query.executeUpdate();
    }
}
