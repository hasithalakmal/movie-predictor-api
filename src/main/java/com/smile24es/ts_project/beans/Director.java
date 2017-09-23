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
@Table(name = "DIRECTOR",catalog = "SMDB", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"DIRECTOR_NAME"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Director.findAll", query = "SELECT d FROM Director d")
    , @NamedQuery(name = "Director.findByDirectorId", query = "SELECT d FROM Director d WHERE d.directorId = :directorId")
    , @NamedQuery(name = "Director.findByDirectorName", query = "SELECT d FROM Director d WHERE d.directorName = :directorName")})
public class Director implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DIRECTOR_ID", nullable = false)
    private Integer directorId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "DIRECTOR_NAME", nullable = false, length = 45)
    private String directorName;
    @JoinColumn(name = "DIRECTOR_CATEGORY", referencedColumnName = "DIRECTOR_CATEGORY_ID")
    @ManyToOne
    private DirectorCategory directorCategory;

    public Director() {
    }

    public Director(Integer directorId) {
        this.directorId = directorId;
    }

    public Director(Integer directorId, String directorName) {
        this.directorId = directorId;
        this.directorName = directorName;
    }

    public Integer getDirectorId() {
        return directorId;
    }

    public void setDirectorId(Integer directorId) {
        this.directorId = directorId;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public DirectorCategory getDirectorCategory() {
        return directorCategory;
    }

    public void setDirectorCategory(DirectorCategory directorCategory) {
        this.directorCategory = directorCategory;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (directorId != null ? directorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Director)) {
            return false;
        }
        Director other = (Director) object;
        if ((this.directorId == null && other.directorId != null) || (this.directorId != null && !this.directorId.equals(other.directorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smile24es.ts_project.beans.Director[ directorId=" + directorId + " ]";
    }
    
}
