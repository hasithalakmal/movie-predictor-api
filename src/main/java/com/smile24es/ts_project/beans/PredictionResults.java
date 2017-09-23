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
public class PredictionResults {
    private NiveBaseResult niveByarseResult;
    
    private CartResult cartResult;

    public NiveBaseResult getNiveByarseResult() {
        return niveByarseResult;
    }

    public void setNiveByarseResult(NiveBaseResult niveByarseResult) {
        this.niveByarseResult = niveByarseResult;
    }

    public CartResult getCartResult() {
        return cartResult;
    }

    public void setCartResult(CartResult cartResult) {
        this.cartResult = cartResult;
    }
    
}
