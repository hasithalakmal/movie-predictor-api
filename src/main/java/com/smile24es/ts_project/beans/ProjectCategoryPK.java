/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smile24es.ts_project.beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author hasithagamage
 */
@Embeddable
public class ProjectCategoryPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "PROJECT_ID", nullable = false)
    private int projectId;
    @Basic(optional = false)
    @Column(name = "CATEGORY_ID", nullable = false)
    private int categoryId;

    public ProjectCategoryPK() {
    }

    public ProjectCategoryPK(int projectId, int categoryId) {
        this.projectId = projectId;
        this.categoryId = categoryId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) projectId;
        hash += (int) categoryId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjectCategoryPK)) {
            return false;
        }
        ProjectCategoryPK other = (ProjectCategoryPK) object;
        if (this.projectId != other.projectId) {
            return false;
        }
        if (this.categoryId != other.categoryId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smile24es.ts_project.beans.ProjectCategoryPK[ projectId=" + projectId + ", categoryId=" + categoryId + " ]";
    }
    
}
