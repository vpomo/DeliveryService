package com.vpomo.deliveryservice.model;

/**
 * Created by Zver on 04.11.2016.
 */

public class ResponseCostCDEK {

    private CostCDEK result;

    public ResponseCostCDEK() {
        this.result = new CostCDEK();
    }

    public CostCDEK getResult() {
        return result;
    }

    public void setResult(CostCDEK result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return result.toString();
    }

}
