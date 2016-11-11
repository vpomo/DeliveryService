package com.vpomo.deliveryservice.model;

/**
 * @author Pomogalov V.A.
 */

public class AddedServiceOrderCDEK {
    private int code;
    private double summa;

    public AddedServiceOrderCDEK() {

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public double getSumma() {
        return summa;
    }

    public void setSumma(double summa) {
        this.summa = summa;
    }

    public String toString() {
        return "AddedServiceCDEK{" + "code=" + code + ", sum=" + summa + "}";
    }
}
