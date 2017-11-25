/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smile24es.ts_project.beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hasithagamage
 */
@Entity
@Table(name = "PRE_PROCESSED_DATA", catalog = "SMDB", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ROW_DATA_ID"})
    , @UniqueConstraint(columnNames = {"MOVE_NAME"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PreProcessedData.findAll", query = "SELECT p FROM PreProcessedData p")
    , @NamedQuery(name = "PreProcessedData.findByRowDataId", query = "SELECT p FROM PreProcessedData p WHERE p.rowDataId = :rowDataId")
    , @NamedQuery(name = "PreProcessedData.findByMoveName", query = "SELECT p FROM PreProcessedData p WHERE p.moveName = :moveName")
    , @NamedQuery(name = "PreProcessedData.findByGenre", query = "SELECT p FROM PreProcessedData p WHERE p.genre = :genre")
    , @NamedQuery(name = "PreProcessedData.findByLanauge", query = "SELECT p FROM PreProcessedData p WHERE p.lanauge = :lanauge")
    , @NamedQuery(name = "PreProcessedData.findByCountry", query = "SELECT p FROM PreProcessedData p WHERE p.country = :country")
    , @NamedQuery(name = "PreProcessedData.findByIsProfitable", query = "SELECT p FROM PreProcessedData p WHERE p.isProfitable = :isProfitable")})
public class PreProcessedData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ROW_DATA_ID", nullable = false)
    private Integer rowDataId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "MOVE_NAME", nullable = false, length = 45)
    private String moveName;
    @Size(max = 45)
    @Column(length = 45)
    private String genre;
    @Size(max = 45)
    @Column(length = 45)
    private String lanauge;
    @Size(max = 45)
    @Column(length = 45)
    private String country;
    @Size(max = 45)
    @Column(name = "IS_PROFITABLE", length = 45)
    private String isProfitable;
    @JoinColumn(name = "ACTOR_CATEGORY", referencedColumnName = "ACTOR_CATEGORY_ID")
    @ManyToOne
    private ActorCategory actorCategory;
    @JoinColumn(name = "BUDGET_CATEGORY", referencedColumnName = "BUDGET_CATEGORY_ID")
    @ManyToOne
    private BudgetCategory budgetCategory;
    @JoinColumn(name = "DIRETOR_CATEGORY", referencedColumnName = "DIRECTOR_CATEGORY_ID")
    @ManyToOne
    private DirectorCategory diretorCategory;
    @JoinColumn(name = "DURATION_CATEGORY", referencedColumnName = "DURATON_CATEGORY_ID")
    @ManyToOne
    private DuratonCategory durationCategory;

    public PreProcessedData() {
    }

    public PreProcessedData(Integer rowDataId) {
        this.rowDataId = rowDataId;
    }

    public PreProcessedData(Integer rowDataId, String moveName) {
        this.rowDataId = rowDataId;
        this.moveName = moveName;
    }

    public Integer getRowDataId() {
        return rowDataId;
    }

    public void setRowDataId(Integer rowDataId) {
        this.rowDataId = rowDataId;
    }

    public String getMoveName() {
        return moveName;
    }

    public void setMoveName(String moveName) {
        this.moveName = moveName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLanauge() {
        return lanauge;
    }

    public void setLanauge(String lanauge) {
        this.lanauge = lanauge;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIsProfitable() {
        return isProfitable;
    }

    public void setIsProfitable(String isProfitable) {
        this.isProfitable = isProfitable;
    }

    public ActorCategory getActorCategory() {
        return actorCategory;
    }

    public void setActorCategory(ActorCategory actorCategory) {
        this.actorCategory = actorCategory;
    }

    public BudgetCategory getBudgetCategory() {
        return budgetCategory;
    }

    public void setBudgetCategory(BudgetCategory budgetCategory) {
        this.budgetCategory = budgetCategory;
    }

    public DirectorCategory getDiretorCategory() {
        return diretorCategory;
    }

    public void setDiretorCategory(DirectorCategory diretorCategory) {
        this.diretorCategory = diretorCategory;
    }

    public DuratonCategory getDurationCategory() {
        return durationCategory;
    }

    public void setDurationCategory(DuratonCategory durationCategory) {
        this.durationCategory = durationCategory;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rowDataId != null ? rowDataId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PreProcessedData)) {
            return false;
        }
        PreProcessedData other = (PreProcessedData) object;
        if ((this.rowDataId == null && other.rowDataId != null) || (this.rowDataId != null && !this.rowDataId.equals(other.rowDataId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smile24es.ts_project.beans.PreProcessedData[ rowDataId=" + rowDataId + " ]";
    }
    
}
