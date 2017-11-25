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
@Table(name = "ROW_DATA", catalog = "SMDB", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ROW_DATA_ID"})
    , @UniqueConstraint(columnNames = {"MOVE_NAME"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RowData.findAll", query = "SELECT r FROM RowData r")
    , @NamedQuery(name = "RowData.findByRowDataId", query = "SELECT r FROM RowData r WHERE r.rowDataId = :rowDataId")
    , @NamedQuery(name = "RowData.findByMoveName", query = "SELECT r FROM RowData r WHERE r.moveName = :moveName")
    , @NamedQuery(name = "RowData.findByDuration", query = "SELECT r FROM RowData r WHERE r.duration = :duration")
    , @NamedQuery(name = "RowData.findByDiretorName", query = "SELECT r FROM RowData r WHERE r.diretorName = :diretorName")
    , @NamedQuery(name = "RowData.findByActorName", query = "SELECT r FROM RowData r WHERE r.actorName = :actorName")
    , @NamedQuery(name = "RowData.findByGenre", query = "SELECT r FROM RowData r WHERE r.genre = :genre")
    , @NamedQuery(name = "RowData.findByBudget", query = "SELECT r FROM RowData r WHERE r.budget = :budget")
    , @NamedQuery(name = "RowData.findByLanauge", query = "SELECT r FROM RowData r WHERE r.lanauge = :lanauge")
    , @NamedQuery(name = "RowData.findByCountry", query = "SELECT r FROM RowData r WHERE r.country = :country")
    , @NamedQuery(name = "RowData.findByIncome", query = "SELECT r FROM RowData r WHERE r.income = :income")
    , @NamedQuery(name = "RowData.findByIsProfitable", query = "SELECT r FROM RowData r WHERE r.isProfitable = :isProfitable")})
public class RowData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ROW_DATA_ID", nullable = false)
    private Integer rowDataId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "MOVE_NAME", nullable = false, length = 500)
    private String moveName;
    private Integer duration;
    @Size(max = 45)
    @Column(name = "DIRETOR_NAME", length = 45)
    private String diretorName;
    @Size(max = 45)
    @Column(name = "ACTOR_NAME", length = 45)
    private String actorName;
    @Size(max = 45)
    @Column(length = 45)
    private String genre;
    private Integer budget;
    @Size(max = 45)
    @Column(length = 45)
    private String lanauge;
    @Size(max = 45)
    @Column(length = 45)
    private String country;
    private Integer income;
    @Size(max = 45)
    @Column(name = "IS_PROFITABLE", length = 45)
    private String isProfitable;

    public RowData() {
    }

    public RowData(Integer rowDataId) {
        this.rowDataId = rowDataId;
    }

    public RowData(Integer rowDataId, String moveName) {
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getDiretorName() {
        return diretorName;
    }

    public void setDiretorName(String diretorName) {
        this.diretorName = diretorName;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
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

    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    public String getIsProfitable() {
        return isProfitable;
    }

    public void setIsProfitable(String isProfitable) {
        this.isProfitable = isProfitable;
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
        if (!(object instanceof RowData)) {
            return false;
        }
        RowData other = (RowData) object;
        if ((this.rowDataId == null && other.rowDataId != null) || (this.rowDataId != null && !this.rowDataId.equals(other.rowDataId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RowData{" + "rowDataId=" + rowDataId + ", moveName=" + moveName + ", duration=" + duration + ", diretorName=" + diretorName + ", actorName=" + actorName + ", genre=" + genre + ", budget=" + budget + ", lanauge=" + lanauge + ", country=" + country + ", income=" + income + ", isProfitable=" + isProfitable + '}';
    }

    
    
}
