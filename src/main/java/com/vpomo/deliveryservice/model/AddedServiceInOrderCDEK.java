package com.vpomo.deliveryservice.model;

/**
 * @author Pomogalov V.A.
 *
 * Вспомогательный объект для формирования дополнительных сервисов
 * Используется при формировании нового заказа на доставку в компании СДЭК
 */

public class AddedServiceInOrderCDEK {
    private int code;
    private double summa;

    public AddedServiceInOrderCDEK() {
    }

    public AddedServiceInOrderCDEK(int code, double summa) {
        this.code = code;
        this.summa = summa;
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
        return "{code=" + code + ", sum=" + summa + "}";
    }
}
