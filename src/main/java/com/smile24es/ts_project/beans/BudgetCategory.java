/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smile24es.ts_project.beans;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hasithagamage
 */
@Entity
@Table(name = "BUDGET_CATEGORY", catalog = "SMDB", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"BUDGET_CATEGORY_NAME"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BudgetCategory.findAll", query = "SELECT b FROM BudgetCategory b")
    , @NamedQuery(name = "BudgetCategory.findByBudgetCategoryId", query = "SELECT b FROM BudgetCategory b WHERE b.budgetCategoryId = :budgetCategoryId")
    , @NamedQuery(name = "BudgetCategory.findByBudgetCategoryName", query = "SELECT b FROM BudgetCategory b WHERE b.budgetCategoryName = :budgetCategoryName")
    , @NamedQuery(name = "BudgetCategory.findByBudgetCategoryMin", query = "SELECT b FROM BudgetCategory b WHERE b.budgetCategoryMin = :budgetCategoryMin")
    , @NamedQuery(name = "BudgetCategory.findByBudgetCategoryMax", query = "SELECT b FROM BudgetCategory b WHERE b.budgetCategoryMax = :budgetCategoryMax")})
public class BudgetCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "BUDGET_CATEGORY_ID", nullable = false)
    private Integer budgetCategoryId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "BUDGET_CATEGORY_NAME", nullable = false, length = 45)
    private String budgetCategoryName;
    @Column(name = "BUDGET_CATEGORY_MIN")
    private Integer budgetCategoryMin;
    @Column(name = "BUDGET_CATEGORY_MAX")
    private Integer budgetCategoryMax;
//    @OneToMany(mappedBy = "budgetCategory")
//    private Collection<PreProcessedData> preProcessedDataCollection;

    public BudgetCategory() {
    }

    public BudgetCategory(Integer budgetCategoryId) {
        this.budgetCategoryId = budgetCategoryId;
    }

    public BudgetCategory(Integer budgetCategoryId, String budgetCategoryName) {
        this.budgetCategoryId = budgetCategoryId;
        this.budgetCategoryName = budgetCategoryName;
    }

    public Integer getBudgetCategoryId() {
        return budgetCategoryId;
    }

    public void setBudgetCategoryId(Integer budgetCategoryId) {
        this.budgetCategoryId = budgetCategoryId;
    }

    public String getBudgetCategoryName() {
        return budgetCategoryName;
    }

    public void setBudgetCategoryName(String budgetCategoryName) {
        this.budgetCategoryName = budgetCategoryName;
    }

    public Integer getBudgetCategoryMin() {
        return budgetCategoryMin;
    }

    public void setBudgetCategoryMin(Integer budgetCategoryMin) {
        this.budgetCategoryMin = budgetCategoryMin;
    }

    public Integer getBudgetCategoryMax() {
        return budgetCategoryMax;
    }

    public void setBudgetCategoryMax(Integer budgetCategoryMax) {
        this.budgetCategoryMax = budgetCategoryMax;
    }

//    @XmlTransient
//    public Collection<PreProcessedData> getPreProcessedDataCollection() {
//        return preProcessedDataCollection;
//    }
//
//    public void setPreProcessedDataCollection(Collection<PreProcessedData> preProcessedDataCollection) {
//        this.preProcessedDataCollection = preProcessedDataCollection;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (budgetCategoryId != null ? budgetCategoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BudgetCategory)) {
            return false;
        }
        BudgetCategory other = (BudgetCategory) object;
        if ((this.budgetCategoryId == null && other.budgetCategoryId != null) || (this.budgetCategoryId != null && !this.budgetCategoryId.equals(other.budgetCategoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smile24es.ts_project.beans.BudgetCategory[ budgetCategoryId=" + budgetCategoryId + " ]";
    }
    
}
