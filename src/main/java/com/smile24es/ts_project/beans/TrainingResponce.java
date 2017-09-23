/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smile24es.ts_project.beans;

/**
 *
 * @author hasithagamage
 */
public class TrainingResponce {
     private ListOfLikelihoodTables likelihoodTables;
     private String cartURL;

    public ListOfLikelihoodTables getLikelihoodTables() {
        return likelihoodTables;
    }

    public void setLikelihoodTables(ListOfLikelihoodTables likelihoodTables) {
        this.likelihoodTables = likelihoodTables;
    }

    public String getCartURL() {
        return cartURL;
    }

    public void setCartURL(String cartURL) {
        this.cartURL = cartURL;
    }
     
     
}
