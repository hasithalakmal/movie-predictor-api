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
public class TestResults {
    private NiveBayearsTestResult niveBayearsTestResult;

    private CartTestResult cartTestResult;

    public NiveBayearsTestResult getNiveBayearsTestResult() {
        return niveBayearsTestResult;
    }

    public void setNiveBayearsTestResult(NiveBayearsTestResult niveBayearsTestResult) {
        this.niveBayearsTestResult = niveBayearsTestResult;
    }

    public CartTestResult getCartTestResult() {
        return cartTestResult;
    }

    public void setCartTestResult(CartTestResult cartTestResult) {
        this.cartTestResult = cartTestResult;
    }

    @Override
    public String toString() {
        return "TestResults{" +
                "niveBayearsTestResult=" + niveBayearsTestResult +
                ", cartTestResult=" + cartTestResult +
                '}';
    }
}
