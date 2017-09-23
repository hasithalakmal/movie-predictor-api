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
@Table(name = "DURATON_CATEGORY", catalog = "SMDB", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"DURATON_CATEGORY_NAME"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DuratonCategory.findAll", query = "SELECT d FROM DuratonCategory d")
    , @NamedQuery(name = "DuratonCategory.findByDuratonCategoryId", query = "SELECT d FROM DuratonCategory d WHERE d.duratonCategoryId = :duratonCategoryId")
    , @NamedQuery(name = "DuratonCategory.findByDuratonCategoryName", query = "SELECT d FROM DuratonCategory d WHERE d.duratonCategoryName = :duratonCategoryName")
    , @NamedQuery(name = "DuratonCategory.findByDuratonCategoryMin", query = "SELECT d FROM DuratonCategory d WHERE d.duratonCategoryMin = :duratonCategoryMin")
    , @NamedQuery(name = "DuratonCategory.findByDuratonCategoryMax", query = "SELECT d FROM DuratonCategory d WHERE d.duratonCategoryMax = :duratonCategoryMax")})
public class DuratonCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DURATON_CATEGORY_ID", nullable = false)
    private Integer duratonCategoryId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "DURATON_CATEGORY_NAME", nullable = false, length = 45)
    private String duratonCategoryName;
    @Column(name = "DURATON_CATEGORY_MIN")
    private Integer duratonCategoryMin;
    @Column(name = "DURATON_CATEGORY_MAX")
    private Integer duratonCategoryMax;
//    @OneToMany(mappedBy = "durationCategory")
//    private Collection<PreProcessedData> preProcessedDataCollection;

    public DuratonCategory() {
    }

    public DuratonCategory(Integer duratonCategoryId) {
        this.duratonCategoryId = duratonCategoryId;
    }

    public DuratonCategory(Integer duratonCategoryId, String duratonCategoryName) {
        this.duratonCategoryId = duratonCategoryId;
        this.duratonCategoryName = duratonCategoryName;
    }

    public Integer getDuratonCategoryId() {
        return duratonCategoryId;
    }

    public void setDuratonCategoryId(Integer duratonCategoryId) {
        this.duratonCategoryId = duratonCategoryId;
    }

    public String getDuratonCategoryName() {
        return duratonCategoryName;
    }

    public void setDuratonCategoryName(String duratonCategoryName) {
        this.duratonCategoryName = duratonCategoryName;
    }

    public Integer getDuratonCategoryMin() {
        return duratonCategoryMin;
    }

    public void setDuratonCategoryMin(Integer duratonCategoryMin) {
        this.duratonCategoryMin = duratonCategoryMin;
    }

    public Integer getDuratonCategoryMax() {
        return duratonCategoryMax;
    }

    public void setDuratonCategoryMax(Integer duratonCategoryMax) {
        this.duratonCategoryMax = duratonCategoryMax;
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
        hash += (duratonCategoryId != null ? duratonCategoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DuratonCategory)) {
            return false;
        }
        DuratonCategory other = (DuratonCategory) object;
        if ((this.duratonCategoryId == null && other.duratonCategoryId != null) || (this.duratonCategoryId != null && !this.duratonCategoryId.equals(other.duratonCategoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smile24es.ts_project.beans.DuratonCategory[ duratonCategoryId=" + duratonCategoryId + " ]";
    }
    
}
