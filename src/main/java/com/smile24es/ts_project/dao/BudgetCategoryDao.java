package com.smile24es.ts_project.dao;

import com.smile24es.ts_project.beans.Actor;
import com.smile24es.ts_project.beans.BudgetCategory;
import com.smile24es.ts_project.beans.DuratonCategory;

import java.util.List;

/**
 * Created by hasithagamage on 5/15/17.
 */
public interface BudgetCategoryDao {

    List<BudgetCategory> findAllBudgetCategory();

    void saveBudgetCategory(BudgetCategory budgetCategory);

    BudgetCategory findBudgetCategoryByName(String budgetCategoryName);

    void deleteAllBudgetCategories();
}
