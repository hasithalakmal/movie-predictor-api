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
@Table(name = "ACTOR_CATEGORY", catalog = "SMDB", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ACTOR_CATEGORY_NAME"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActorCategory.findAll", query = "SELECT a FROM ActorCategory a")
    , @NamedQuery(name = "ActorCategory.findByActorCategoryId", query = "SELECT a FROM ActorCategory a WHERE a.actorCategoryId = :actorCategoryId")
    , @NamedQuery(name = "ActorCategory.findByActorCategoryName", query = "SELECT a FROM ActorCategory a WHERE a.actorCategoryName = :actorCategoryName")})
public class ActorCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ACTOR_CATEGORY_ID", nullable = false)
    private Integer actorCategoryId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ACTOR_CATEGORY_NAME", nullable = false, length = 45)
    private String actorCategoryName;
//    @OneToMany(mappedBy = "actorCategory")
//    private Collection<Actor> actorCollection;
//    @OneToMany(mappedBy = "actorCategory")
//    private Collection<PreProcessedData> preProcessedDataCollection;

    public ActorCategory() {
    }

    public ActorCategory(Integer actorCategoryId) {
        this.actorCategoryId = actorCategoryId;
    }

    public ActorCategory(Integer actorCategoryId, String actorCategoryName) {
        this.actorCategoryId = actorCategoryId;
        this.actorCategoryName = actorCategoryName;
    }

    public Integer getActorCategoryId() {
        return actorCategoryId;
    }

    public void setActorCategoryId(Integer actorCategoryId) {
        this.actorCategoryId = actorCategoryId;
    }

    public String getActorCategoryName() {
        return actorCategoryName;
    }

    public void setActorCategoryName(String actorCategoryName) {
        this.actorCategoryName = actorCategoryName;
    }

//    @XmlTransient
//    public Collection<Actor> getActorCollection() {
//        return actorCollection;
//    }
//
//    public void setActorCollection(Collection<Actor> actorCollection) {
//        this.actorCollection = actorCollection;
//    }
//
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
        hash += (actorCategoryId != null ? actorCategoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActorCategory)) {
            return false;
        }
        ActorCategory other = (ActorCategory) object;
        if ((this.actorCategoryId == null && other.actorCategoryId != null) || (this.actorCategoryId != null && !this.actorCategoryId.equals(other.actorCategoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smile24es.ts_project.beans.ActorCategory[ actorCategoryId=" + actorCategoryId + " ]";
    }
    
}
