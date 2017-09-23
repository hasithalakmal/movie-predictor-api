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
@Table(name = "DIRECTOR_CATEGORY", catalog = "SMDB", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"DIRECTOR_CATEGORY_NAME"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DirectorCategory.findAll", query = "SELECT d FROM DirectorCategory d")
    , @NamedQuery(name = "DirectorCategory.findByDirectorCategoryId", query = "SELECT d FROM DirectorCategory d WHERE d.directorCategoryId = :directorCategoryId")
    , @NamedQuery(name = "DirectorCategory.findByDirectorCategoryName", query = "SELECT d FROM DirectorCategory d WHERE d.directorCategoryName = :directorCategoryName")})
public class DirectorCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DIRECTOR_CATEGORY_ID", nullable = false)
    private Integer directorCategoryId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "DIRECTOR_CATEGORY_NAME", nullable = false, length = 45)
    private String directorCategoryName;
//    @OneToMany(mappedBy = "diretorCategory")
//    private Collection<PreProcessedData> preProcessedDataCollection;
//    @OneToMany(mappedBy = "directorCategory")
//    private Collection<Director> directorCollection;

    public DirectorCategory() {
    }

    public DirectorCategory(Integer directorCategoryId) {
        this.directorCategoryId = directorCategoryId;
    }

    public DirectorCategory(Integer directorCategoryId, String directorCategoryName) {
        this.directorCategoryId = directorCategoryId;
        this.directorCategoryName = directorCategoryName;
    }

    public Integer getDirectorCategoryId() {
        return directorCategoryId;
    }

    public void setDirectorCategoryId(Integer directorCategoryId) {
        this.directorCategoryId = directorCategoryId;
    }

    public String getDirectorCategoryName() {
        return directorCategoryName;
    }

    public void setDirectorCategoryName(String directorCategoryName) {
        this.directorCategoryName = directorCategoryName;
    }

//    @XmlTransient
//    public Collection<PreProcessedData> getPreProcessedDataCollection() {
//        return preProcessedDataCollection;
//    }
//
//    public void setPreProcessedDataCollection(Collection<PreProcessedData> preProcessedDataCollection) {
//        this.preProcessedDataCollection = preProcessedDataCollection;
//    }
//
//    @XmlTransient
//    public Collection<Director> getDirectorCollection() {
//        return directorCollection;
//    }
//
//    public void setDirectorCollection(Collection<Director> directorCollection) {
//        this.directorCollection = directorCollection;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (directorCategoryId != null ? directorCategoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DirectorCategory)) {
            return false;
        }
        DirectorCategory other = (DirectorCategory) object;
        if ((this.directorCategoryId == null && other.directorCategoryId != null) || (this.directorCategoryId != null && !this.directorCategoryId.equals(other.directorCategoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smile24es.ts_project.beans.DirectorCategory[ directorCategoryId=" + directorCategoryId + " ]";
    }
    
}
