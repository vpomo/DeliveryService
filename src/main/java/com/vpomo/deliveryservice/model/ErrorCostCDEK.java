package com.vpomo.deliveryservice.model;

/**
 * @author Pomogalov V.A.
 *
 * Объект для чтения ошибки от сервера при расчете стоимости доставки
 */


public class ErrorCostCDEK {
    private int code;
    private String text;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String toString() {
        return "ErrorCDEK{" + "code=" + code + ", text=" + text + "}";
    }
}
