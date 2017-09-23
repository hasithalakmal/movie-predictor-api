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
@Table(name = "ACTOR", catalog = "SMDB", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ACTOR_NAME"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actor.findAll", query = "SELECT a FROM Actor a")
    , @NamedQuery(name = "Actor.findByActorId", query = "SELECT a FROM Actor a WHERE a.actorId = :actorId")
    , @NamedQuery(name = "Actor.findByActorName", query = "SELECT a FROM Actor a WHERE a.actorName = :actorName")})
public class Actor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ACTOR_ID", nullable = false)
    private Integer actorId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ACTOR_NAME", nullable = false, length = 45)
    private String actorName;
    @JoinColumn(name = "ACTOR_CATEGORY", referencedColumnName = "ACTOR_CATEGORY_ID")
    @ManyToOne
    private ActorCategory actorCategory;

    public Actor() {
    }

    public Actor(Integer actorId) {
        this.actorId = actorId;
    }

    public Actor(Integer actorId, String actorName) {
        this.actorId = actorId;
        this.actorName = actorName;
    }

    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public ActorCategory getActorCategory() {
        return actorCategory;
    }

    public void setActorCategory(ActorCategory actorCategory) {
        this.actorCategory = actorCategory;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (actorId != null ? actorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actor)) {
            return false;
        }
        Actor other = (Actor) object;
        if ((this.actorId == null && other.actorId != null) || (this.actorId != null && !this.actorId.equals(other.actorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smile24es.ts_project.beans.Actor[ actorId=" + actorId + " ]";
    }
    
}
