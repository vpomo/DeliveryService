package com.vpomo.deliveryservice.model;

/**
 * @author Pomogalov V.A.
 *
 * Вспомогательный объект для чтения ответа от сервера СДЭК
 * при расчете стоимости доставки товара
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
